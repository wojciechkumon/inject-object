package pl.wojtas626.injectobject;

import pl.wojtas626.injectobject.internal.Binder;

public interface InstanceProxy<T> {

  T getInstance(Binder binder);

}
