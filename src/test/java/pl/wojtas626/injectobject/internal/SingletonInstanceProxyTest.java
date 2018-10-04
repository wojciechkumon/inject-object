package pl.wojtas626.injectobject.internal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SingletonInstanceProxyTest {

  private Binder binder;
  private Object object;

  private SingletonInstanceProxy<Object> proxy;

  @Before
  public void setUp() {
    binder = mock(Binder.class);
    object = new Object();
    proxy = new SingletonInstanceProxy<>(Object.class);
    when(binder.createInstance(Object.class)).thenReturn(object);
  }

  @Test
  public void shouldReturnTwiceSameInstance() {
    Object actual = proxy.getInstance(binder);
    assertSame(object, actual);

    actual = proxy.getInstance(binder);
    assertSame(object, actual);

    verify(binder, times(1)).createInstance(Object.class);
    verify(binder, times(1)).initSetters(object);
  }

}
