package rocks.danielw.junit5features;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

class ConditionalTestExecutionTest {

  @Test
  @EnabledOnOs(OS.MAC)
  void testOnlyOnMacOS() {

  }

  @Test
  @EnabledOnJre(JRE.JAVA_11)
  void testOnlyOnJava11() {

  }

  @Test
  @EnabledOnJre({JRE.JAVA_10, JRE.JAVA_11})
  void testOnlyOnJava10And11() {

  }

  @Test
  @EnabledIf("Math.random() > 0.314159")
  void testIfConditionIsTrue() {
    System.out.println("Enable condition was true");
  }

  @Test
  @DisabledIf("Math.random() > 0.314159")
  void testIfConditionIsFalse() {
    System.out.println("Disable condition was true");
  }

  @Test
  @EnabledOnOs({OS.LINUX, OS.SOLARIS })                            // disabled on all but Linux, Solaris
  @DisabledOnJre(JRE.JAVA_8)                                       // disabled on Java 8
  @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")  // disabled on all but 64bit OS
  @EnabledIfEnvironmentVariable(named = "ENV", matches = "ci")     // disabled unless `ENV` is `ci`
  void test() {
    // doesn't run on Linux with Java 10 because `@EnabledOnOs` doesn't
    // really _enable_ the test as much as _not disable_ it
  }

}
