package pl.wojtas626.injectobject.internal;

public interface Binder {

  <T> T createInstance(Class<T> clazz);

  <T> T initSetters(T instance);

}
