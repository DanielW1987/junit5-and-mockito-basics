package rocks.danielw.mockito.examples;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 *https://github.com/mockito/mockito/wiki/Using-Spies-(and-Fakes)
 *
 * Based on the role played during testing, there can be different types of test doubles. There are some other types of test doubles,
 * such as dummy objects, fake objects, and stubs. What makes a mock object different from the others is that it has behavior verification.
 * That means the mock object verifies that it (the mock object) is being used correctly by the object under test. If the verification
 * succeeds, you can conclude the object under test will correctly use the real collaborator.
 *
 * Spies, on the other hand, provides a way to spy on a real object. With a spy, you can call all the real underlying methods of the
 * object while still tracking every interaction, just as you would with a mock.
 *
 * Things get a bit different for Mockito mocks vs. spies. A Mockito mock allows us to stub a method call. That means we can stub a method
 * to return a specific object. For example, we can mock a Spring Data JPA repository in a service class to stub a getProduct() method of
 * the repository to return a Product object. To run the test, we don’t need the database to be up and running – a pure unit test.
 *
 * A Mockito spy is a partial mock. We can mock a part of the object by stubbing few methods, while real method invocations will be used
 * for the other. By saying so, we can conclude that calling a method on a spy will invoke the actual method, unless we explicitly stub
 * the method, and therefore the term partial mock.
 *
 * Now – let's discuss the difference between Mock and Spy in Mockito – not the theoretical differences between the two concepts, just
 * how they differ within Mockito itself. When Mockito creates a mock – it does so from the Class of a Type, not from an actual instance.
 * The mock simply creates a bare-bones shell instance of the Class, entirely instrumented to track interactions with it. On the other
 * hand, the spy will wrap an existing instance. It will still behave in the same way as the normal instance – the only difference is
 * that it will also be instrumented to track all the interactions with it.
 */
class SpiesTest implements WithAssertions, WithMockito {

  @Spy
  private List<String> spyList = new ArrayList<>();

  @Test
  void whenSpyingOnList_thenObjectIsSpied() {
    List<String> list = new ArrayList<>();
    List<String> spyList = Mockito.spy(list);

    // calls real add() method
    spyList.add("one");
    spyList.add("two");

    // we can also verify interaction with spy
    Mockito.verify(spyList).add("one");
    Mockito.verify(spyList).add("two");

    assertThat(spyList.size()).isEqualTo(2);
  }

  @Test
  void whenUsingTheSpyAnnotation_thenObjectIsSpied() {
    spyList.add("one");
    spyList.add("two");

    Mockito.verify(spyList).add("one");
    Mockito.verify(spyList).add("two");

    assertThat(spyList.size()).isEqualTo(2);
  }

  @Test
  void whenStubASpy_thenStubbed() {
    List<String> list = new ArrayList<>();
    List<String> spyList = Mockito.spy(list);

    assertThat(spyList.size()).isEqualTo(0);
    when(spyList.size()).thenReturn(100);
    assertThat(spyList.size()).isEqualTo(100);
  }

}
