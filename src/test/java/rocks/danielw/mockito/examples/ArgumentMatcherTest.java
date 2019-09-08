package rocks.danielw.mockito.examples;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import rocks.danielw.model.Owner;
import rocks.danielw.repository.CrudRepository;
import rocks.danielw.service.DummyService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ArgumentMatcherTest implements WithAssertions, WithMockito {

  @Mock
  CrudRepository<Long, Owner> repository;

  @InjectMocks
  DummyService service;

  @Test
  void testCreate() {
    Owner dummyOwner = Owner.builder().firstName("John").lastName("Doe").address("123 Street").telephone("123456789").build();
    Mockito.when(repository.create(any(Owner.class))).thenReturn(1L); // there are a bunch more any-methods in class ArgumentMatchers

    Long id = service.create(dummyOwner);

    // test mock interactions
    verify(repository, times(1)).create(any(Owner.class));

    // test result
    assertThat(id).isEqualTo(1);
  }

}
