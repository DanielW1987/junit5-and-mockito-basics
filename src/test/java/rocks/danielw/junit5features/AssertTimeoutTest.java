package rocks.danielw.junit5features;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class AssertTimeoutTest {

  @Test
  @Tag("fail")
  void testTimeout() {
    // test fails, because the timeout is less then sleep time
    // Nevertheless, the executable will be executed completely
    assertTimeout(Duration.ofMillis(100), () -> {
      Thread.sleep(2000);
      System.out.println("test timeout");
    });
  }

  @Test
  @Tag("fail")
  void testTimeoutPreemptively() {
    // test fails, because the timeout is less then sleep time
    // The executable will not be executed, because it's preemptively
    assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
      Thread.sleep(2000);
      System.out.println("You never see this");
    });
  }

}
