package pl.wojtas626.injectobject.internal;

import org.junit.Test;
import pl.wojtas626.injectobject.InstanceProxy;

import static org.junit.Assert.assertEquals;

public class InstanceProxyFactoryTest {

  @Test
  public void shouldCreateSpecifiedProxy() {
    InstanceProxy<Object> proxy = InstanceProxyFactory.create(new Object());

    assertEquals(SpecifiedInstanceProxy.class, proxy.getClass());
  }

  @Test
  public void shouldCreateSingletonProxy() {
    InstanceProxy<Object> proxy =
        InstanceProxyFactory.create(Object.class, InstanceProxyType.SINGLETON);

    assertEquals(SingletonInstanceProxy.class, proxy.getClass());
  }

  @Test
  public void shouldCreatePrototypeProxy() {
    InstanceProxy<Object> proxy =
        InstanceProxyFactory.create(Object.class, InstanceProxyType.PROTOTYPE);

    assertEquals(PrototypeInstanceProxy.class, proxy.getClass());
  }

  @Test
  public void shouldCreateSingletonProviderProxy() {
    InstanceProxy<Object> proxy =
        InstanceProxyFactory.create(Object.class, InstanceProxyType.SINGLETON_PROVIDER);

    assertEquals(SingletonProviderInstanceProxy.class, proxy.getClass());
  }

  @Test
  public void shouldCreatePrototypeProviderProxy() {
    InstanceProxy<Object> proxy =
        InstanceProxyFactory.create(Object.class, InstanceProxyType.PROTOTYPE_PROVIDER);

    assertEquals(PrototypeProviderInstanceProxy.class, proxy.getClass());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowException() {
    InstanceProxyFactory.create(Object.class, null);
  }

}
