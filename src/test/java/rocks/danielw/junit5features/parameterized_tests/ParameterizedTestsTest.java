package rocks.danielw.junit5features.parameterized_tests;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import rocks.danielw.model.Season;
import rocks.danielw.util.StringUtils;

import java.util.stream.Stream;

class ParameterizedTestsTest implements WithAssertions {

  @DisplayName("@ValueSource")
  @ParameterizedTest(name = ParameterizedTest.ARGUMENTS_PLACEHOLDER)
  @ValueSource(strings = {"Java", "Kotlin", "Groovy", "JavaScript"})
  void testValueSource(String value) {
    assertThat(StringUtils.startsWithCapitalLetter(value)).isTrue();
  }

  @DisplayName("@EnumSource")
  @ParameterizedTest()
  @EnumSource(Season.class)
  void testEnumSource(Season season) {
    System.out.println(season);
  }

  @DisplayName("@CSVSource")
  @ParameterizedTest()
  @CsvSource({
          "FL, 1, 1",
          "OH, 2, 2",
          "MI, 1, 3"
  })
  void testCsvSource(String stateName, int value1, int value2) {
    System.out.println(stateName + " = " + value1 + " : " + value2);
  }

  @DisplayName("@CsvFileSource")
  @ParameterizedTest()
  @CsvFileSource(resources = "/test.csv", numLinesToSkip = 1)
  void testCsvFileSource(String stateName, int value1, int value2) {
    System.out.println(stateName + " = " + value1 + " : " + value2);
  }

  @DisplayName("@MethodSource")
  @ParameterizedTest()
  @MethodSource("stateValueProvider")
  void testCsvFileSource(String state, int intValue, boolean booleanValue) {
    System.out.println(state + " = " + intValue + " : " + booleanValue);
  }

  private static Stream<Arguments> stateValueProvider() {
    return Stream.of(
            Arguments.of("FL", 5, true),
            Arguments.of("OH", 4, false),
            Arguments.of("MI", 1, true)
    );
  }

  @DisplayName("@ArgumentsSource")
  @ParameterizedTest()
  @ArgumentsSource(CustomArgumentsProvider.class)
  void testMethodSource(String value) {
    assertThat(StringUtils.startsWithCapitalLetter(value)).isTrue();
  }

}
