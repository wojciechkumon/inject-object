package pl.wojtas626.injectobject.internal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import javax.inject.Inject;

public class AnnotatedMethodsFinderImplTest {

  private AnnotatedMethodsFinderImpl finder;

  @Before
  public void setUp() {
    finder = new AnnotatedMethodsFinderImpl();
  }

  @Test
  public void shouldReturnInjectConstructor() throws NoSuchMethodException {
    Constructor<InjectClass> actualConstructor = finder.getInjectOrDefaultConstructor(InjectClass.class);
    Constructor<InjectClass> expectedConstructor = InjectClass.class.getConstructor(String.class);

    Assert.assertEquals(expectedConstructor, actualConstructor);
  }

  @Test
  public void shouldReturnDefaultConstructor() throws NoSuchMethodException {
    Constructor<EmptyClass> actualConstructor = finder.getInjectOrDefaultConstructor(EmptyClass.class);
    Constructor<EmptyClass> expectedConstructor = EmptyClass.class.getConstructor();

    Assert.assertEquals(expectedConstructor, actualConstructor);
  }

  @Test(expected = RuntimeException.class)
  public void shouldNotFindConstructor() {
    finder.getInjectOrDefaultConstructor(NoInjectClass.class);
  }

  @Test(expected = RuntimeException.class)
  public void shouldNotFindConstructorWhenTwoInjectConstructors() {
    finder.getInjectOrDefaultConstructor(DoubleInjectClass.class);
  }

  @Test
  public void shouldReturnInjectMethods() throws NoSuchMethodException {
    List<Method> actualMethods = finder.getInjectMethods(InjectClass.class);

    Assert.assertEquals(2, actualMethods.size());
    Assert.assertTrue(actualMethods.contains(InjectClass.class.getMethod("setY", String.class)));
    Assert.assertTrue(actualMethods.contains(InjectClass.class.getMethod("setZ", String.class)));
  }

  @Test
  public void shouldReturnEmptyList() throws NoSuchMethodException {
    List<Method> actualMethods = finder.getInjectMethods(NoInjectClass.class);

    Assert.assertTrue(actualMethods.isEmpty());
  }

  public static class InjectClass {
    private final String x;
    private String y;
    private String z;

    @Inject
    public InjectClass(String x) {
      this.x = x;
    }

    @Inject
    public void setY(String y) {
      this.y = y;
    }

    @Inject
    public void setZ(String z) {
      this.z = z;
    }
  }

  public static class NoInjectClass {
    private final String x;
    private String y;
    private String z;

    public NoInjectClass(String x) {
      this.x = x;
    }

    public void setY(String y) {
      this.y = y;
    }
  }

  public static class EmptyClass {
  }

  public static class DoubleInjectClass {
    private final String x;

    @Inject
    public DoubleInjectClass(String x) {
      this.x = x;
    }

    @Inject
    public DoubleInjectClass(String x, String y) {
      this.x = x + y;
    }
  }

}
