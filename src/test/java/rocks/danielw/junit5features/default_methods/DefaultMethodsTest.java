package rocks.danielw.junit5features.default_methods;

import org.junit.jupiter.api.Test;

class DefaultMethodsTest implements DefaultInterface {

  @Test
  void dummyTest() {
    assertThat(true).isTrue();
  }

}
