package pl.wojtas626.injectobject.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

interface AnnotatedMethodsFinder {

  <T> Constructor<T> getInjectOrDefaultConstructor(Class<T> clazz);

  <T> List<Method> getInjectMethods(Class<T> clazz);

}
