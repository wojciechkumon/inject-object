package pl.wojtas626.injectobject.internal;

import pl.wojtas626.injectobject.InstanceProxy;

import javax.inject.Provider;

class PrototypeProviderInstanceProxy<T> implements InstanceProxy<T> {
  private final Class<? extends Provider<T>> providerClass;
  private Provider<T> provider;

  PrototypeProviderInstanceProxy(Class<? extends Provider<T>> providerClass) {
    this.providerClass = providerClass;
  }

  @Override
  public T getInstance(Binder binder) {
    if (provider == null) {
      provider = binder.createInstance(providerClass);
    }
    return provider.get();
  }

}
