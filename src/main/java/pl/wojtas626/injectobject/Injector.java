package pl.wojtas626.injectobject;

public interface Injector {

  <T> T get(Class<T> clazz);

}
