package rocks.danielw.junit5features.extension;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({TimingExtension.class})
class TimingExtensionTest implements WithAssertions {

  @Test
  void test() {
    assertThat("Jupiter").isEqualTo("Jupiter");
  }

}
