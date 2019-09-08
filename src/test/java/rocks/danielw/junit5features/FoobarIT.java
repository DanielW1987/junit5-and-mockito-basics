package rocks.danielw.junit5features;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

/*
 * IntegrationTest
 * Can be run separately from unit tests with the Maven Failsafe plugin and the verify lifecycle phase.
 */
class FoobarIT implements WithAssertions {

  @Test
  void integrationTest() {
    assertThat("Hello World").isEqualTo("Hello World");
  }

}
