package pl.wojtas626.injectobject.internal;

import org.junit.Before;
import org.junit.Test;

import javax.inject.Provider;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SingletonProviderInstanceProxyTest {
  private Binder binder;

  private SingletonProviderInstanceProxy<Object> proxy;

  @Before
  public void setUp() {
    binder = mock(Binder.class);
    proxy = new SingletonProviderInstanceProxy<>(ObjectProvider.class);
    when(binder.createInstance(ObjectProvider.class)).thenReturn(new ObjectProvider());
  }

  @Test
  public void shouldReturnTwiceSameInstance() {
    Object first = proxy.getInstance(binder);
    Object second = proxy.getInstance(binder);

    assertSame(first, second);

    verify(binder, times(1)).createInstance(ObjectProvider.class);
  }


  private static class ObjectProvider implements Provider<Object> {

    @Override
    public Object get() {
      return new Object();
    }
  }

}
