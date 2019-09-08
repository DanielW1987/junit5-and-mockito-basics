package rocks.danielw.junit5features;

import org.junit.jupiter.api.Test;

class AssumptionTest {

  /*
   * A set of methods useful for stating assumptions about the conditions in which a test is meaningful.
   * A failed assumption does not mean the code is broken, but that the test provides no useful information.
   * Assume basically means "don't run this test if these conditions don't apply". The default JUnit runner
   * skips tests with failing assumptions. Custom runners may behave differently. A good example of using
   * assumptions is in Theories where they are needed to exclude certain datapoints that aren't suitable or
   * allowed for a certain test case. Failed assumptions are usually not logged, because there may be many
   * tests that don't apply to certain configurations.
   *
   * see https://junit.org/junit5/docs/5.0.0/api/org/junit/jupiter/api/Assumptions.html
   */

  @Test
  void testAssumption() {
    // assumeTrue();
    // ToDo DanielW: Implement example
  }

  @Test
  void testAssumptionIfTrue() {
    // assumeTrue();
    // ToDo DanielW: Implement example
  }

}
