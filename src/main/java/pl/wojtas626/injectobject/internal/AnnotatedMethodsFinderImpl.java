package pl.wojtas626.injectobject.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

class AnnotatedMethodsFinderImpl implements AnnotatedMethodsFinder {

  @Override
  @SuppressWarnings("unchecked")
  public <T> Constructor<T> getInjectOrDefaultConstructor(Class<T> clazz) {
    Constructor<?> injectConstructor = findInjectConstructor(clazz);
    if (injectConstructor != null) {
      return (Constructor<T>) injectConstructor;
    }
    return findDefaultConstructor(clazz);
  }

  private <T> Constructor<?> findInjectConstructor(Class<T> clazz) {
    Constructor<?> foundConstructor = null;

    for (Constructor<?> constructor : clazz.getConstructors()) {
      foundConstructor = handleConstructor(foundConstructor, constructor, clazz);
    }
    return foundConstructor;
  }

  private <T> Constructor<?> handleConstructor(Constructor<?> constructorAlreadyFound,
                                               Constructor<?> constructor, Class<T> clazz) {
    if (constructor.getAnnotation(Inject.class) != null) {
      if (constructorAlreadyFound != null) {
        throw new RuntimeException("Class has more then 1 constructor with @Inject annotation for class: "
            + clazz.getName());
      }
      return constructor;
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  private <T> Constructor<T> findDefaultConstructor(Class<T> clazz) {
    for (Constructor<?> constructor : clazz.getConstructors()) {
      if (constructor.getParameterCount() == 0) {
        return (Constructor<T>) constructor;
      }
    }
    throw new RuntimeException("No inject or default constructor for class: " + clazz.getName());
  }

  @Override
  public <T> List<Method> getInjectMethods(Class<T> clazz) {
    List<Method> injectMethods = new ArrayList<>();
    for (Method method : clazz.getDeclaredMethods()) {
      addMethodIfInject(injectMethods, method);
    }
    return injectMethods;
  }

  private void addMethodIfInject(List<Method> injectMethods, Method method) {
    if (method.getAnnotation(Inject.class) != null) {
      injectMethods.add(method);
    }
  }

}
