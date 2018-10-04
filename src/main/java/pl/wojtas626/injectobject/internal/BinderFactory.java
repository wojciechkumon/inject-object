package pl.wojtas626.injectobject.internal;

import pl.wojtas626.injectobject.InstanceProxy;

import java.util.Map;

class BinderFactory {

  private BinderFactory() {}

  static Binder create(Map<Class<?>, InstanceProxy<?>> instanceProxies) {
    return new BinderImpl(AnnotatedMethodsFinderFactory.create(), instanceProxies);
  }

}
