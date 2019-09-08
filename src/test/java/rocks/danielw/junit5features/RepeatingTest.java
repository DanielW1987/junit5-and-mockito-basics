package rocks.danielw.junit5features;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.RepeatedTest.LONG_DISPLAY_NAME;

class RepeatingTest implements WithAssertions {

  @DisplayName("My repeated test")
  @RepeatedTest(value = 5, name = LONG_DISPLAY_NAME)
  void repeatedTest() {
    assertThat("Hello World").isEqualTo("Hello World");
  }

}
