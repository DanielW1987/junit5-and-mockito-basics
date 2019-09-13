package rocks.danielw.mockito.examples;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rocks.danielw.message.Message;
import rocks.danielw.message.MessageController;
import rocks.danielw.message.MessageService;

class CustomArgumentMatcher implements WithMockito, WithAssertions {

  @Mock
  private MessageService service;

  @InjectMocks
  private MessageController controller;
  /*
   * Creating our matcher can be good to select the best possible approach for a given scenario and produce highest quality test, which is clean and maintainable.
   * For instance, we could have a MessageController that delivers messages. It'll receive a MessageDTO, and from that, It'll create a Message to be delivered by
   * MessageService. Our verification will be simple, verify that we called the MessageService exactly 1 time with any Message:
   *
   *    verify(messageService, times(1)).deliverMessage(any(Message.class));
   *
   * Because the Message is constructed inside the method under test, we’re forced to use any() as the matcher.
   * This approach doesn't let us validate the data inside the Message, which can be different compared to the data inside MessageDTO.
   * For that reason, we're going to implement a custom argument matcher.
   */

  @Test
  void testCustomArgumentMatcher() {

  }

}

class MessageMatcher implements ArgumentMatcher<Message> {

  private Message left;

  MessageMatcher(Message left) {
    this.left = left;
  }

  @Override
  public boolean matches(Message right) {
    return left.equals(right);
  }
}
