package rocks.danielw.mockito.examples;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rocks.danielw.mockito.examples.dummys.Controller;
import rocks.danielw.mockito.examples.dummys.Service;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/*
 * For simple stubbing we use thenReturn() or thenThrow() in case of exception. If your stubbed method needs to return a result
 * based on some computation then you can use the Answer interface. We can stub the mock object’s method in such a way that when
 * the stubbed method is invoked, the answer(invocation) method of the Answer is called. The Answer object’s answer() method will
 * return the dynamic result.
 */
class AnswersTest implements WithAssertions, WithMockito {

  @Mock
  private Service service;

  @InjectMocks
  private Controller controller;

  @Captor
  private ArgumentCaptor<String> captor;

  @BeforeEach
  void setup() {
    // Depending on the last name, an empty list, a list with one element and a list with several elements are to be returned.
    when(service.findAllByLastName(anyString())).thenAnswer(invocationOnMock -> {
      String name = invocationOnMock.getArgument(0);
      if (name.equals("%Doe%")) {
        return List.of("John Doe", "Maria Doe", "Hannes Doe");
      }
      else if (name.equals("%Thompson%")) {
        return List.of("Maria Thompson");
      }
      else {
        return List.of();
      }
    });
  }

  @Test
  void testProceedMany() {
    // given
    final String LAST_NAME = "Doe";

    // when
    String view = controller.proceed(LAST_NAME);

    // then
    verify(service, times(1)).findAllByLastName(captor.capture());
    assertThat(view).isEqualTo("view/list");
    assertThat(captor.getValue()).isEqualTo("%" + LAST_NAME + "%");
  }

  @Test
  void testProceedSingle() {
    // given
    final String LAST_NAME = "Thompson";

    // when
    String view = controller.proceed(LAST_NAME);

    // then
    verify(service, times(1)).findAllByLastName(captor.capture());
    assertThat(view).isEqualTo("view/single");
    assertThat(captor.getValue()).isEqualTo("%" + LAST_NAME + "%");
  }

  @Test
  void testProceedNothing() {
    // given
    final String LAST_NAME = "Any";

    // when
    String view = controller.proceed(LAST_NAME);

    // then
    verify(service, times(1)).findAllByLastName(captor.capture());
    assertThat(view).isEqualTo("view/empty");
    assertThat(captor.getValue()).isEqualTo("%" + LAST_NAME + "%");
  }

}
