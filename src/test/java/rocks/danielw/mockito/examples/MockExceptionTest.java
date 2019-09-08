package rocks.danielw.mockito.examples;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rocks.danielw.model.Owner;
import rocks.danielw.repository.CrudRepository;
import rocks.danielw.service.DummyService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class MockExceptionTest implements WithAssertions, WithMockito {

  @Mock
  CrudRepository<Long, Owner> repository;

  @InjectMocks
  DummyService service;

  @Test
  void testDoThrow() {
    doThrow(new RuntimeException("Booom!")).when(repository).delete(anyLong());

    boolean result = service.delete(1L);

    // test mock interactions
    verify(repository, times(1)).delete(anyLong());

    // test result
    assertThat(result).isFalse();
  }

}