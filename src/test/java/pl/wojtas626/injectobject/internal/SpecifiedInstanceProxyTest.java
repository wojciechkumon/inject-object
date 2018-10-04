package pl.wojtas626.injectobject.internal;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class SpecifiedInstanceProxyTest {

  private Binder binder;

  @Before
  public void setUp() {
    binder = mock(Binder.class);
  }

  @Test
  public void shouldReturnInstance() {
    ArrayList<?> arrayList = new ArrayList<>();
    SpecifiedInstanceProxy<List> proxy = new SpecifiedInstanceProxy<>(arrayList);

    List actual = proxy.getInstance(binder);

    assertEquals(arrayList, actual);
  }

}
