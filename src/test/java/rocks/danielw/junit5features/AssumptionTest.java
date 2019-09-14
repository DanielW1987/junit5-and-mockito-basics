package rocks.danielw.junit5features;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

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
  void testAssumeTrue() {
    assumeTrue(ZoneId.systemDefault().getId().equals("America/New_York"), "applicable time zone assumption");
    System.out.println("assumption passed");
    System.out.println("test continues");
  }

  @Test
  void testOnDev() {
    System.setProperty("ENV", "DEV");
    assumeTrue("DEV".equals(System.getProperty("ENV")), "Test execution skipped");
    // remainder of test will proceed
  }

  @Test
  void testOnProd() {
    System.setProperty("ENV", "PROD");
    assumeTrue("DEV".equals(System.getProperty("ENV")), "Test execution skipped");
    // remainder of test will be aborted
  }

}
