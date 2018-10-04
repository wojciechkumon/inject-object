package pl.wojtas626.injectobject;

import pl.wojtas626.injectobject.internal.InstanceProxyFactory;
import pl.wojtas626.injectobject.internal.InstanceProxyType;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Provider;

public abstract class AbstractAppConfig implements AppConfig {
  private final Map<Class<?>, InstanceProxy<?>> instanceProxyMappings = new HashMap<>();

  @Override
  public final Map<Class<?>, InstanceProxy<?>> getInstanceProxyMap() {
    return Collections.unmodifiableMap(new HashMap<>(instanceProxyMappings));
  }

  protected <T> void bindSingleton(Class<T> from, Class<? extends T> implementation) {
    addToMap(from, InstanceProxyFactory.create(implementation, InstanceProxyType.SINGLETON));
  }

  protected <T> void bindSingleton(List<Class<?>> fromList, Class<? extends T> implementation) {
    addToMap(fromList, implementation,
        InstanceProxyFactory.create(implementation, InstanceProxyType.SINGLETON));
  }

  protected <T> void bindPrototype(Class<T> from, Class<? extends T> implementation) {
    addToMap(from, InstanceProxyFactory.create(implementation, InstanceProxyType.PROTOTYPE));
  }

  protected <T> void bindPrototype(List<Class<?>> fromList, Class<? extends T> implementation) {
    addToMap(fromList, implementation,
        InstanceProxyFactory.create(implementation, InstanceProxyType.PROTOTYPE));
  }

  protected <T> void bind(Class<T> from, T instance) {
    addToMap(from, InstanceProxyFactory.create(instance));
  }

  protected <T> void bind(List<Class<?>> from, T instance) {
    addToMap(from, instance, InstanceProxyFactory.create(instance));
  }

  protected <T> void bindSingletonProvider(Class<T> from, Class<? extends Provider<T>> provider) {
    addToMap(from, InstanceProxyFactory.create(provider, InstanceProxyType.SINGLETON_PROVIDER));
  }

  protected <T> void bindPrototypeProvider(Class<T> from, Class<? extends Provider<T>> provider) {
    addToMap(from, InstanceProxyFactory.create(provider, InstanceProxyType.PROTOTYPE_PROVIDER));
  }

  private <T> void addToMap(Class<T> from, InstanceProxy<?> instanceProxy) {
    instanceProxyMappings.put(from, instanceProxy);
  }

  private <T> void addToMap(List<Class<?>> fromList, Class<? extends T> implementation,
                            InstanceProxy<? extends T> instanceProxy) {
    fromList.forEach(from -> checkIfSubclass(from, implementation));
    fromList.forEach(from -> instanceProxyMappings.put(from, instanceProxy));
  }

  private <T> void addToMap(List<Class<?>> fromList, T instance,
                            InstanceProxy<? extends T> instanceProxy) {
    fromList.forEach(from -> checkIfSubclass(from, instance.getClass()));
    fromList.forEach(from -> instanceProxyMappings.put(from, instanceProxy));
  }

  private void checkIfSubclass(Class<?> from, Class<?> implementation) {
    if (!from.isAssignableFrom(implementation)) {
      throw new IllegalArgumentException("Can't bind implementation: "
          + implementation.getName() + " to: " + from.getName());
    }
  }

}
