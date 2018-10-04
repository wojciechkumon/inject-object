package pl.wojtas626.injectobject;

import pl.wojtas626.injectobject.internal.InjectorFactory;

public class InjectObject {

  private InjectObject() {}

  public static Injector createInjector(AppConfig appConfig) {
    return InjectorFactory.createInjector(appConfig);
  }

}
