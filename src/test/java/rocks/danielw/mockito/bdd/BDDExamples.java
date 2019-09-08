package rocks.danielw.mockito.bdd;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rocks.danielw.mockito.examples.WithMockito;
import rocks.danielw.model.Owner;
import rocks.danielw.repository.CrudRepository;
import rocks.danielw.service.DummyService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

/*
 * The BDDMockito class is just another approach. There are equivalents within the Mockito class.
 */
class BDDExamples implements WithAssertions, WithMockito {

  @Mock
  CrudRepository<Long, Owner> repository;

  @InjectMocks
  DummyService service;


  @Test
  void testFindById() {
    // given
    Owner dummyOwner = Owner.builder().id(1L).firstName("John").lastName("Doe").address("123 Street").telephone("123456789").build();
    given(repository.find(1L)).willReturn(dummyOwner);

    // when
    Optional<Owner> owner = service.findById(1L);

    // then
    then(repository).should(times(1)).find(1L);
    assertThat(owner).isNotNull();
    assertThat(owner.isPresent()).isTrue();
    assertThat(owner.get()).isEqualTo(dummyOwner);
  }

  @Test
  void testDeleteThrowsException() {
    // given
    given(repository.delete(anyLong())).willThrow(new RuntimeException("boom!"));

    // when
    boolean result = service.delete(1L);

    // then
    then(repository).should().delete(anyLong());
    assertThat(result).isFalse();
  }

}
