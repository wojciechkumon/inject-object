package pl.wojtas626.injectobject.internal;

import pl.wojtas626.injectobject.InstanceProxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

class BinderImpl implements Binder {
  private final AnnotatedMethodsFinder annotatedMethodsFinder;
  private final Map<Class<?>, InstanceProxy<?>> instanceProxies;

  BinderImpl(AnnotatedMethodsFinder annotatedMethodsFinder, Map<Class<?>, InstanceProxy<?>> instanceProxies) {
    this.annotatedMethodsFinder = annotatedMethodsFinder;
    this.instanceProxies = instanceProxies;
  }

  @Override
  public <T> T createInstance(Class<T> clazz) {
    try {
      return tryToCreateInstance(clazz);
    } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  private <T> T tryToCreateInstance(Class<T> clazz)
      throws InstantiationException, IllegalAccessException, InvocationTargetException {
    Constructor<T> constructor = annotatedMethodsFinder.getInjectOrDefaultConstructor(clazz);
    if (noArgsConstructor(constructor)) {
      return clazz.newInstance();
    }
    return createInstanceWithBindedArgs(constructor);
  }

  private <T> T createInstanceWithBindedArgs(Constructor<T> constructor)
      throws IllegalAccessException, InvocationTargetException, InstantiationException {
    return constructor.newInstance(getArgsValues(constructor));
  }

  private <T> boolean noArgsConstructor(Constructor<T> constructor) {
    return constructor.getParameterCount() == 0;
  }

  private Object bindObject(Class<?> clazz) {
    InstanceProxy<?> instanceProxy = instanceProxies.get(clazz);
    checkInstanceProxyNotNull(clazz, instanceProxy);
    return instanceProxy.getInstance(this);
  }

  private void checkInstanceProxyNotNull(Class<?> clazz, InstanceProxy<?> instanceProxy) {
    if (instanceProxy == null) {
      throw new IllegalArgumentException("No mapping for: " + clazz.getName());
    }
  }

  @Override
  public <T> T initSetters(T instance) {
    List<Method> injectMethods = annotatedMethodsFinder.getInjectMethods(instance.getClass());
    injectMethods.forEach(method -> bindAndInvoke(method, instance));
    return instance;
  }

  private <T> void bindAndInvoke(Method method, T instance) {
    try {
      method.invoke(instance, getArgsValues(method));
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

  private Object[] getArgsValues(Executable executable) {
    Class<?>[] parameterTypes = executable.getParameterTypes();
    Object[] bindedParameters = new Object[parameterTypes.length];
    for (int i = 0; i < parameterTypes.length; i++) {
      bindedParameters[i] = bindObject(parameterTypes[i]);
    }
    return bindedParameters;
  }

}
