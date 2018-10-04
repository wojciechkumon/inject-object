package pl.wojtas626.injectobject;


import java.util.Map;

public interface AppConfig {

  void configure();

  Map<Class<?>, InstanceProxy<?>> getInstanceProxyMap();

}
