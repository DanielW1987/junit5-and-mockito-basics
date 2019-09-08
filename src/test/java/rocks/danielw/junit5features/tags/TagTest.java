package rocks.danielw.junit5features.tags;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("my-tag")
class TagTest implements WithAssertions {

  @Test
  void dummyTest() {
    assertThat(true).isTrue();
  }

}
