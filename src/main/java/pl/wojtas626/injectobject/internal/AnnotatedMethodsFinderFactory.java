package pl.wojtas626.injectobject.internal;

class AnnotatedMethodsFinderFactory {

  private AnnotatedMethodsFinderFactory() {}

  static AnnotatedMethodsFinder create() {
    return new AnnotatedMethodsFinderImpl();
  }

}
