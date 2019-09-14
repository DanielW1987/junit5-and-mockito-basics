package rocks.danielw.mockito.examples;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;

/*
 * Mockito ArgumentCaptor is used to capture arguments for mocked methods.
 * ArgumentCaptor is used with Mockito verify() methods to get the arguments
 * passed when any method is called. This way, we can provide additional
 * JUnit assertions for our tests.
 *
 * Warning: it is recommended to use ArgumentCaptor with verification but not with stubbing.
 * Using ArgumentCaptor with stubbing may decrease test readability because captor is created
 * outside of assert (aka verify or 'then') block. Also it may reduce defect localization because
 * if stubbed method was not called then no argument is captured. In a way ArgumentCaptor is
 * related to custom argument matchers. Both techniques can be used for making sure certain
 * arguments where passed to mocks. However, ArgumentCaptor may be a better fit if:
 *
 *  - custom argument matcher is not likely to be reused
 *  - you just need it to assert on argument values to complete verification
 *
 * Custom argument matchers via ArgumentMatcher are usually better for stubbing.
 */
class ArgumentCaptorTest implements WithAssertions, WithMockito {

  @Mock
  private Service service;

  @InjectMocks
  private Controller controller;

  @Captor
  ArgumentCaptor<String> stringArgumentCaptor;

  @Test
  void argumentCaptorTest() {
    // given
    final String LAST_NAME = "Doe";
    final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

    // when
    controller.proceed("Doe");

    // then
    verify(service, times(1)).findAllByLastName(captor.capture());
    assertThat(captor.getValue()).isEqualTo("%Doe%");
  }

  @Test
  void annotationBasedArgumentCaptorTest() {
    // when
    controller.proceed("Doe");

    // then
    verify(service, times(1)).findAllByLastName(stringArgumentCaptor.capture());
    assertThat(stringArgumentCaptor.getValue()).isEqualTo("%Doe%");
  }

  @Test
  void capturingVarArgs() {
    // given
    ArgumentCaptor<Person> varArgs = ArgumentCaptor.forClass(Person.class);

    // when
    controller.proceedPersons();

    // then
    verify(service, times(1)).varArgMethod(varArgs.capture());
    assertThat(varArgs.getAllValues()).containsExactly(new Person("John", "Doe"), new Person("Thomas", "Doe"));
  }

  @Test
  void capturingMoreThanOneMethodParameter() {
    MathUtils mockMathUtils = mock(MathUtils.class);
    when(mockMathUtils.add(1, 1)).thenReturn(2);
    when(mockMathUtils.isInteger(anyString())).thenReturn(true);

    ArgumentCaptor<Integer> acInteger = ArgumentCaptor.forClass(Integer.class);
    ArgumentCaptor<String> acString  = ArgumentCaptor.forClass(String.class);

    assertThat(mockMathUtils.add(1, 1)).isEqualTo(2);
    assertThat(mockMathUtils.isInteger("1")).isTrue();
    assertThat(mockMathUtils.isInteger("999")).isTrue();

    verify(mockMathUtils).add(acInteger.capture(), acInteger.capture());
    assertThat(acInteger.getAllValues()).containsExactly(1, 1);

    verify(mockMathUtils, times(2)).isInteger(acString.capture());
    assertThat(acString.getAllValues()).containsExactly("1", "999");
  }

}

class Controller {

  private final Service service;

  public Controller(Service service) {
    this.service = service;
  }

  void proceed(String lastName) {
    List<String> allByLastName = service.findAllByLastName("%" + lastName + "%");
    // do something with list...
  }

  void proceedPersons() {
    service.varArgMethod(new Person("John", "Doe"), new Person("Thomas", "Doe"));
  }

}

class Service {

  List<String> findAllByLastName(String lastName) {
    return List.of();
  }

  void varArgMethod(Person... persons) {
    // do something with persons...
  }

}

class Person {

  private String firstName;
  private String lastName;

  Person(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person person = (Person) o;
    return firstName.equals(person.firstName) &&
            lastName.equals(person.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }
}

class MathUtils {

  public int add(int x, int y) {
    return x + y;
  }

  public boolean isInteger(String s) {
    try {
      Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  public long squareLong(long l) {
    return l*l;
  }
}
