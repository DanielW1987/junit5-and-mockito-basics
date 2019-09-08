package rocks.danielw.mockito.init;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

class InlineMockTest implements WithAssertions {

  @Test
  void testInlineMock() {
    Map mapMock = Mockito.mock(Map.class);

    assertThat(mapMock.size()).isEqualTo(0);
  }

}
