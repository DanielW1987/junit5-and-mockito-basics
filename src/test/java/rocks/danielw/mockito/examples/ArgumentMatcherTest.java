package rocks.danielw.mockito.examples;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.*;
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

  @InjectMocks
  DummyService service;

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

    Long id = service.create(dummyOwner);

    // test mock interactions
    verify(repository, times(1)).create(any(Owner.class));

    // test result
    assertThat(id).isEqualTo(1);
  }

  @Test
  void testArgThat() {
    // ToDo DanielW: Implement me
    Owner dummyOwner = Owner.builder().firstName("John").lastName("Doe").address("123 Street").telephone("123456789").build();

    // Use argThat() if you want your mock to return other objects depending on passed arguments
    BDDMockito.given(repository.create(argThat(arg -> arg.getFirstName().equals("John")))).willReturn(1L);
    //doReturn(1L).when(repository.create(argThat(arg -> arg.getFirstName().equals("John"))));
    //doReturn(2L).when(repository.create(argThat(arg -> arg.getFirstName().equals("Max"))));

    // Mockito.when(repository.create(argThat(arg -> arg.getFirstName().equals("John")))).thenReturn(1L);
    // Mockito.when(repository.create(argThat(arg -> arg.getFirstName().equals("Max")))).thenReturn(2L);

    Long id = service.create(dummyOwner);

    // test mock interactions
    verify(repository, times(1)).create(any(Owner.class));

    // test result
    assertThat(id).isEqualTo(1);
  }

}
