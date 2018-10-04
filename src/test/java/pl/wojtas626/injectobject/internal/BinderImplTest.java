package pl.wojtas626.injectobject.internal;

import org.junit.Before;
import org.junit.Test;
import pl.wojtas626.injectobject.InstanceProxy;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BinderImplTest {

  private AnnotatedMethodsFinder finder;
  private Map<Class<?>, InstanceProxy<?>> proxyMap;

  private BinderImpl binder;


  @Before
  public void setUp() {
    finder = mock(AnnotatedMethodsFinder.class);
    proxyMap = new HashMap<>();
    binder = new BinderImpl(finder, proxyMap);
  }

  @Test
  public void shouldCreateSimpleObject() throws NoSuchMethodException {
    when(finder.getInjectOrDefaultConstructor(Object.class))
        .thenReturn(Object.class.getConstructor());

    Object object = binder.createInstance(Object.class);
    assertEquals(Object.class, object.getClass());
  }

  @Test
  public void shouldCreateObjectWithConstructorParam() throws NoSuchMethodException {
    Object object = new Object();
    proxyMap.put(Object.class, binder -> object);
    when(finder.getInjectOrDefaultConstructor(ObjectWrapper.class))
        .thenReturn(ObjectWrapper.class.getConstructor(Object.class));

    ObjectWrapper wrapper = binder.createInstance(ObjectWrapper.class);
    assertEquals(ObjectWrapper.class, wrapper.getClass());
    assertEquals(object, wrapper.getObject());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenNoInstanceProxy() throws NoSuchMethodException {
    when(finder.getInjectOrDefaultConstructor(ObjectWrapper.class))
        .thenReturn(ObjectWrapper.class.getConstructor(Object.class));
    binder.createInstance(ObjectWrapper.class);
  }

  @Test
  public void initSetters() throws NoSuchMethodException {
    Object object = new Object();
    proxyMap.put(Object.class, binder -> object);
    when(finder.getInjectMethods(ObjectWrapper.class)).thenReturn(
        singletonList(ObjectWrapper.class.getMethod("setObject", Object.class)));
    ObjectWrapper wrapper = new ObjectWrapper(null);

    binder.initSetters(wrapper);

    assertEquals(object, wrapper.getObject());
  }

  public static class ObjectWrapper {
    private Object object;

    public ObjectWrapper(Object object) {
      this.object = object;
    }

    public void setObject(Object o) {
      this.object = o;
    }

    public Object getObject() {
      return object;
    }
  }

}
