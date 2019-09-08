package rocks.danielw.junit5features.default_methods;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // necessary, because @BeforeAll is only allowed on static methods
public interface DefaultInterface extends WithAssertions {

  @BeforeAll
  default void beforeAll() {
    System.out.println("Do something useful before all!");
  }

}
