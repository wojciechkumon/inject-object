package pl.wojtas626.injectobject.internal;

import pl.wojtas626.injectobject.AppConfig;
import pl.wojtas626.injectobject.Injector;

public class InjectorFactory {

  private InjectorFactory() {}

  public static Injector createInjector(AppConfig appConfig) {
    appConfig.configure();
    Binder binder = BinderFactory.create(appConfig.getInstanceProxyMap());
    return new InjectorImpl(appConfig.getInstanceProxyMap(), binder);
  }

}
