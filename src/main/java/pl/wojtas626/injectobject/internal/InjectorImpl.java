package pl.wojtas626.injectobject.internal;

import pl.wojtas626.injectobject.Injector;
import pl.wojtas626.injectobject.InstanceProxy;

import java.util.Map;

class InjectorImpl implements Injector {
  private final Map<Class<?>, InstanceProxy<?>> instanceProxies;
  private final Binder binder;

  InjectorImpl(Map<Class<?>, InstanceProxy<?>> instanceProxies, Binder binder) {
    this.instanceProxies = instanceProxies;
    this.binder = binder;
  }

  @Override
  @SuppressWarnings("unchecked")
  public synchronized <T> T get(Class<T> clazz) {
    InstanceProxy<T> instanceProxy = (InstanceProxy<T>) instanceProxies.get(clazz);
    checkInstanceProxyNotNull(clazz, instanceProxy);

    return instanceProxy.getInstance(binder);
  }

  private <T> void checkInstanceProxyNotNull(Class<T> clazz, InstanceProxy<T> instanceProxy) {
    if (instanceProxy == null) {
      throw new IllegalArgumentException("No mapping for: " + clazz.getName());
    }
  }

}
