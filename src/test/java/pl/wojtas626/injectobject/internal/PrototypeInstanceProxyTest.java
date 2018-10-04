package pl.wojtas626.injectobject.internal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PrototypeInstanceProxyTest {

  private Binder binder;

  private PrototypeInstanceProxy<Object> proxy;

  @Before
  public void setUp() {
    binder = mock(Binder.class);
    proxy = new PrototypeInstanceProxy<>(Object.class);
    when(binder.initSetters(any())).thenReturn(new Object(), new Object());
  }

  @Test
  public void shouldAlwaysReturnNewInstance() {
    Object first = proxy.getInstance(binder);
    Object second = proxy.getInstance(binder);

    assertEquals(first.getClass(), second.getClass());
    assertNotSame(first, second);

    verify(binder, times(2)).createInstance(Object.class);
    verify(binder, times(2)).initSetters(any());
  }

}
