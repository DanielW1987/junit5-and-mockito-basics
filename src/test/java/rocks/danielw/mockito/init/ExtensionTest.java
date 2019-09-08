package rocks.danielw.mockito.init;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
class ExtensionTest implements WithAssertions {

  @Mock
  Map<String, String> mapMock;

  @Test
  void testAnnotationMock() {
    assertThat(mapMock.size()).isEqualTo(0);
  }

}
