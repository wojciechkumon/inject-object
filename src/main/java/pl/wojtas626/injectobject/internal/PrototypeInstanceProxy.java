package pl.wojtas626.injectobject.internal;

import pl.wojtas626.injectobject.InstanceProxy;

class PrototypeInstanceProxy<T> implements InstanceProxy<T> {
  private final Class<T> clazz;

  PrototypeInstanceProxy(Class<T> clazz) {
    this.clazz = clazz;
  }

  @Override
  public T getInstance(Binder binder) {
    T instance = binder.createInstance(clazz);
    return binder.initSetters(instance);
  }

}
