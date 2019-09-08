package rocks.danielw.junit5features.tags;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

class DummyModelTest implements ModelTest, WithAssertions {

  @Test
  void dummyTest() {
    assertThat(true).isTrue();
  }

}
