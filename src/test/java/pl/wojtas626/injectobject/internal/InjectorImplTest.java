package pl.wojtas626.injectobject.internal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.wojtas626.injectobject.InstanceProxy;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InjectorImplTest {

  private Map<Class<?>, InstanceProxy<?>> proxies;
  private Binder binder;

  private InjectorImpl injector;

  @Before
  public void setUp() {
    proxies = new HashMap<>();
    binder = Mockito.mock(Binder.class);
    injector = new InjectorImpl(proxies, binder);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void shouldReturnInterfaceImplementation() {
    InstanceProxy<HashMap> proxy = mock(InstanceProxy.class);
    when(proxy.getInstance(binder)).thenReturn(new HashMap());
    proxies.put(Map.class, proxy);

    Map map = injector.get(Map.class);

    Assert.assertEquals(HashMap.class, map.getClass());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionBecauseOfNoMapping() {
    injector.get(Map.class);
  }

}
