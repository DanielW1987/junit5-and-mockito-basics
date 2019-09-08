package rocks.danielw.mockito.init;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

class AnnotationMockTest implements WithAssertions {

  @Mock
  Map<String, String> mapMock;

  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this); // creates a brand new Map mock for each test method
  }

  @Test
  void testAnnotationMock() {
    assertThat(mapMock.size()).isEqualTo(0);
  }

}
