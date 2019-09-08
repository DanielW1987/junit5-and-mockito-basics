package rocks.danielw.junit5features;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import rocks.danielw.util.StringUtils;

import java.util.List;

/**
 * Nested is especially useful for testing utility classes in which methods are overloaded several times.
 * This is because the individual methods do not have so much to do with each other. They are just helper
 * methods without coherent logic. However, if we want to test a domain object, this approach is not
 * particularly suitable. This is because the behavior of the object is often interesting here. Here a
 * Behavior-Driven-Development approach with tests in the "Given-When-Then" style is better suited.
 */
class NestedTest implements WithAssertions {

  @Nested
  class JoinTest {

    @Nested
    class WithIterable {

      @Test
      void test() {
        List<String> values = List.of("a", "b", "c");
        String joinedString = StringUtils.join(values, '-');

        assertThat(joinedString).isEqualTo("a-b-c");
      }

    }

    @Nested
    class WithIterator {

      @Test
      void test() {
        List<String> values = List.of("a", "b", "c");
        String joinedString = StringUtils.join(values.iterator(), '-');

        assertThat(joinedString).isEqualTo("a-b-c");
      }

    }

    @Nested
    class WithObjectArray {

      @Test
      void test() {
        Object[] values = {"a", "b", "c"};
        String joinedString = StringUtils.join(values, '-');

        assertThat(joinedString).isEqualTo("a-b-c");
      }

    }

  }

}
