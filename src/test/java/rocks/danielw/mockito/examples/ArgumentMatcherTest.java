package rocks.danielw.mockito.examples;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import rocks.danielw.message.MessageController;
import rocks.danielw.message.MessageService;
import rocks.danielw.model.Owner;
import rocks.danielw.repository.CrudRepository;
import rocks.danielw.service.DummyService;
import rocks.danielw.service.FlowerService;

import static org.mockito.AdditionalMatchers.and;
import static org.mockito.AdditionalMatchers.or;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ArgumentMatcherTest implements WithAssertions, WithMockito {

  @Mock
  CrudRepository<Long, Owner> repository;

  @Mock
  FlowerService flowerService;

  @Mock
  private MessageService messageService;

  @InjectMocks
  private MessageController controller;

  @InjectMocks
  DummyService dummyService;

  @Test
  void testAnyStringMatcher() {
    doReturn("Flower").when(flowerService).analyze("poppy");
    String analyzeValue = flowerService.analyze("poppy");
    assertThat(analyzeValue).isEqualTo("Flower");

    /*
     * In the above example, the String “Flower” is returned only when the analyze service receive the String “poppy”.
     * But maybe we need to respond to a wider range of values or beforehand unknown values.
     * In all these scenarios, we can configure our mocked methods with argument matchers:
     */
    when(flowerService.analyze(anyString())).thenReturn("Flower");
    analyzeValue = flowerService.analyze("poppy");
    assertThat(analyzeValue).isEqualTo("Flower");
  }

  @Test
  void testEqMatcher() {
    /*
     * In case of a method has more than one argument, it isn't possible to use ArgumentMatchers for only some of the arguments.
     * Mockito requires you to provide all arguments either by matchers or by exact values.
     * A next example is an incorrect approach to this:
     *
     *    when(flowerService.isABigFlower("poppy", anyInt())).thenReturn(true);
     *
     * To fix it and keep the String name “poppy” as it's desired, we'll use eq matcher:
     */
    when(flowerService.isABigFlower(eq("poppy"), anyInt())).thenReturn(true);

    boolean result = flowerService.isABigFlower("poppy", 1);

    assertThat(result).isTrue();
  }

  @Test
  void testWithLogicalOperatorOr() {
    when(flowerService.isABigFlower(or(eq("poppy"), endsWith("y")), anyInt())).thenReturn(true);

    boolean result = flowerService.isABigFlower("harmony", 1);

    assertThat(result).isTrue();
  }

  @Test
  void testWithLogicalOperatorAnd() {
    when(flowerService.isABigFlower(and(eq("poppy"), endsWith("y")), anyInt())).thenReturn(true);

    boolean result = flowerService.isABigFlower("poppy", 1);

    assertThat(result).isTrue();
  }

  @Test
  void testWithLogicalOperatorNot() {
    when(flowerService.isABigFlower(AdditionalMatchers.not(eq("poppy")), anyInt())).thenReturn(true);

    boolean result = flowerService.isABigFlower("other", 1);

    assertThat(result).isTrue();
  }

  @Test
  void testAny() {
    Owner dummyOwner = Owner.builder().firstName("John").lastName("Doe").address("123 Street").telephone("123456789").build();
    Mockito.when(repository.create(any(Owner.class))).thenReturn(1L); // there are a bunch more any-methods in class ArgumentMatchers

    Long id = dummyService.create(dummyOwner);

    // test mock interactions
    verify(repository, times(1)).create(any(Owner.class));

    // test result
    assertThat(id).isEqualTo(1);
  }

  @Test
  void textArgThat() {
    controller.deliverMessage("from", "to", "text");

    // verify that MessageService is called with a message that has a certain value for 'to'
    verify(messageService, times(1)).deliverMessage(argThat(argument -> argument.getText().equals("text")));
  }

}
