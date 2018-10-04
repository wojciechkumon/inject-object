package pl.wojtas626.injectobject.internal;

import pl.wojtas626.injectobject.InstanceProxy;

class SpecifiedInstanceProxy<T> implements InstanceProxy<T> {
  private final T instance;

  SpecifiedInstanceProxy(T instance) {
    this.instance = instance;
  }

  @Override
  public T getInstance(Binder binder) {
    return instance;
  }

}
