package pl.wojtas626.injectobject.internal;

import pl.wojtas626.injectobject.InstanceProxy;

import javax.inject.Provider;

class SingletonProviderInstanceProxy<T> implements InstanceProxy<T> {
  private final Class<? extends Provider<T>> providerClass;
  private T instance;

  SingletonProviderInstanceProxy(Class<? extends Provider<T>> providerClass) {
    this.providerClass = providerClass;
  }

  @Override
  public T getInstance(Binder binder) {
    return getOrCreateInstance(binder);
  }

  private T getOrCreateInstance(Binder binder) {
    if (instance == null) {
      Provider<T> provider = binder.createInstance(providerClass);
      instance = provider.get();
    }
    return instance;
  }

}
