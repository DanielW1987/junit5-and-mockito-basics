package rocks.danielw.junit5features.tags;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

class DummyControllerTest implements ControllerTest, WithAssertions {

  @Test
  void dummyTest() {
    assertThat(true).isTrue();
  }

}
