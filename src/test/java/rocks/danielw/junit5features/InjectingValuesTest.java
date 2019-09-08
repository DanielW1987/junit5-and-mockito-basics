package rocks.danielw.junit5features;

import org.junit.jupiter.api.*;

class InjectingValuesTest {

  @Test
  void testInfo(TestInfo testInfo) {
    System.out.println("Display name: " + testInfo.getDisplayName());
    System.out.println("Tags: " + testInfo.getTags());
    System.out.println("Test class: " + testInfo.getTestClass());
    System.out.println("Test method: " + testInfo.getTestMethod());
  }

  @Test
  void testReporter(TestReporter testReporter) {
    // allows you to publish runtime information for test reporting
    testReporter.publishEntry("author", "John Doe");
  }

  @RepeatedTest(5)
  void repetitionInfo(RepetitionInfo repetitionInfo) {
    System.out.println("Current repetition: " + repetitionInfo.getCurrentRepetition());
    System.out.println("Total repetitions: " + repetitionInfo.getTotalRepetitions());
  }

}
