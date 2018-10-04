package pl.wojtas626.injectobject.internal;

import org.junit.Assert;
import org.junit.Test;

public class AnnotatedMethodsFinderFactoryTest {

  @Test
  public void shouldCreateCorrectObject() {
    AnnotatedMethodsFinder annotatedMethodsFinder = AnnotatedMethodsFinderFactory.create();

    Assert.assertEquals(AnnotatedMethodsFinderImpl.class, annotatedMethodsFinder.getClass());
  }

}
