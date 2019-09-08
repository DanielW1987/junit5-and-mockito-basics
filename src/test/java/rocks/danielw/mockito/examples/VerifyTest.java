package rocks.danielw.mockito.examples;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rocks.danielw.model.Owner;
import rocks.danielw.repository.CrudRepository;
import rocks.danielw.service.DummyService;

import static org.mockito.Mockito.*;

class VerifyTest implements WithAssertions, WithMockito {

  @Mock
  CrudRepository<Long, Owner> repository;

  @InjectMocks
  DummyService service;

  @Test
  void testTimes() {
    service.delete(1L);

    // test mock interactions
    verify(repository, times(1)).delete(1L);
  }

  @Test
  void testAtLeastOnce() {
    service.delete(1L);

    // test mock interactions
    verify(repository, atLeastOnce()).delete(1L);
  }

  @Test
  void testAtMost() {
    service.delete(1L);
    service.delete(1L);

    // test mock interactions
    verify(repository, atMost(2)).delete(1L);
  }

  @Test
  void testAtLeast() {
    service.delete(1L);
    service.delete(1L);
    service.delete(1L);

    // test mock interactions
    verify(repository, atLeast(2)).delete(1L);
  }

  @Test
  void testAtMostOnce() {
    service.delete(1L);

    // test mock interactions
    verify(repository, atMostOnce()).delete(1L);
  }

  @Test
  void testNever() {
    // test mock interactions
    verify(repository, never()).delete(1L);
  }

}
