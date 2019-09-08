package rocks.danielw.mockito.examples;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import rocks.danielw.model.Owner;
import rocks.danielw.repository.CrudRepository;
import rocks.danielw.service.DummyService;

import java.util.Optional;

import static org.mockito.Mockito.*;

class MockMethodTest implements WithAssertions, WithMockito {

  @Mock
  CrudRepository<Long, Owner> repository;

  @InjectMocks
  DummyService service;

  @Test
  void findByIdTest() {
    Owner dummyOwner = Owner.builder().id(1L).firstName("John").lastName("Doe").address("123 Street").telephone("123456789").build();
    Mockito.when(repository.find(1L)).thenReturn(dummyOwner);

    Optional<Owner> owner = service.findById(1L);

    // test mock interactions
    verify(repository, times(1)).find(1L);

    // test result
    assertThat(owner).isNotNull();
    assertThat(owner.isPresent()).isTrue();
    assertThat(owner.get()).isEqualTo(dummyOwner);
  }

  @Test
  void findByIdTest2() {
    Mockito.when(repository.find(1L)).thenReturn(null);

    Optional<Owner> owner = service.findById(1L);

    // test mock interactions
    verify(repository, times(1)).find(1L);

    // test result
    assertThat(owner).isNotNull();
    assertThat(owner.isEmpty()).isTrue();
  }

}
