package pl.wojtas626.injectobject;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class InjectObjectTest {

  @Test
  public void shouldReturnDifferentInjectors() {
    AppConfig appConfig = mock(AppConfig.class);

    Injector first = InjectObject.createInjector(appConfig);
    Injector second = InjectObject.createInjector(appConfig);

    assertNotNull(first);
    assertNotNull(second);
    assertNotSame(first, second);
  }

}
