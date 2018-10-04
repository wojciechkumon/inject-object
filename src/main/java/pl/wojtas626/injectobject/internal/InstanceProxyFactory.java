package pl.wojtas626.injectobject.internal;

import pl.wojtas626.injectobject.InstanceProxy;

import javax.inject.Provider;

public class InstanceProxyFactory {

  private InstanceProxyFactory() {}

  @SuppressWarnings("unchecked")
  public static <T> InstanceProxy<T> create(Class<T> clazz, InstanceProxyType instanceProxyType) {
    if (instanceProxyType == InstanceProxyType.SINGLETON) {
      return new SingletonInstanceProxy<>(clazz);
    } else if (instanceProxyType == InstanceProxyType.PROTOTYPE) {
      return new PrototypeInstanceProxy<>(clazz);
    } else if (instanceProxyType == InstanceProxyType.SINGLETON_PROVIDER) {
      return new SingletonProviderInstanceProxy<>((Class<? extends Provider<T>>) clazz);
    } else if (instanceProxyType == InstanceProxyType.PROTOTYPE_PROVIDER) {
      return new PrototypeProviderInstanceProxy<>((Class<? extends Provider<T>>) clazz);
    }

    throw new IllegalArgumentException("InstanceProxyType not supported: "
        + (instanceProxyType == null ? null : instanceProxyType.name()));
  }

  public static <T> InstanceProxy<T> create(T object) {
    return new SpecifiedInstanceProxy<>(object);
  }

}
