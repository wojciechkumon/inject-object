package pl.wojtas626.injectobject.internal;

import pl.wojtas626.injectobject.InstanceProxy;

class SingletonInstanceProxy<T> implements InstanceProxy<T> {
  private final Class<T> clazz;
  private T instance;

  SingletonInstanceProxy(Class<T> clazz) {
    this.clazz = clazz;
  }

  @Override
  public T getInstance(Binder binder) {
    return getOrCreateInstance(binder);
  }

  private T getOrCreateInstance(Binder binder) {
    if (instance == null) {
      instance = binder.createInstance(clazz);
      binder.initSetters(instance);
    }
    return instance;
  }

}
