package rocks.danielw.mockito.examples;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rocks.danielw.mockito.examples.dummys.Controller;
import rocks.danielw.mockito.examples.dummys.OtherService;
import rocks.danielw.mockito.examples.dummys.Service;

import static org.mockito.Mockito.inOrder;

class InOrderTest implements WithAssertions, WithMockito {

  @Mock
  private Service service;

  @Mock
  private OtherService otherService;

  @InjectMocks
  private Controller controller;

  @Test
  void testOrder() {
    // given
    InOrder inOrder = inOrder(otherService, service); // specify the order

    // when
    controller.executeServices();

    // then test the order
    inOrder.verify(otherService).doSomething();
    inOrder.verify(service).doSomething();
  }

}
