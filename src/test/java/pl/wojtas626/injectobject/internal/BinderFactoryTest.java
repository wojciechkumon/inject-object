package pl.wojtas626.injectobject.internal;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class BinderFactoryTest {

  @Test
  public void shouldCreateCorrectBinder() {
    Binder binder = BinderFactory.create(Collections.emptyMap());

    Assert.assertEquals(BinderImpl.class, binder.getClass());
  }

}
