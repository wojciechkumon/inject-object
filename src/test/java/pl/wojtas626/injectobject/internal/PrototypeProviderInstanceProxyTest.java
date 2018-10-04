package pl.wojtas626.injectobject.internal;

import org.junit.Before;
import org.junit.Test;

import javax.inject.Provider;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PrototypeProviderInstanceProxyTest {

  private Binder binder;

  private PrototypeProviderInstanceProxy<Object> proxy;

  @Before
  public void setUp() {
    binder = mock(Binder.class);
    proxy = new PrototypeProviderInstanceProxy<>(ObjectProvider.class);
    when(binder.createInstance(ObjectProvider.class)).thenReturn(new ObjectProvider());
  }

  @Test
  public void shouldReturnDifferentInstances() {
    Object first = proxy.getInstance(binder);
    Object second = proxy.getInstance(binder);

    assertEquals(first.getClass(), second.getClass());
    assertNotSame(first, second);

    verify(binder, times(1)).createInstance(ObjectProvider.class);
  }


  private static class ObjectProvider implements Provider<Object> {

    @Override
    public Object get() {
      return new Object();
    }
  }

}
