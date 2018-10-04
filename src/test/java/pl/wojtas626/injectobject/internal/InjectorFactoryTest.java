package pl.wojtas626.injectobject.internal;

import org.junit.Assert;
import org.junit.Test;
import pl.wojtas626.injectobject.AppConfig;
import pl.wojtas626.injectobject.Injector;
import org.mockito.Mockito;

public class InjectorFactoryTest {

  @Test
  public void shouldCreateCorrectInjector() {
    AppConfig appConfig = Mockito.mock(AppConfig.class);

    Injector injector = InjectorFactory.createInjector(appConfig);

    Assert.assertEquals(InjectorImpl.class, injector.getClass());
  }

}
