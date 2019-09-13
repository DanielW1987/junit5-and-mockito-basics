package rocks.danielw.message;

public class MessageController {

  private final MessageService messageService;

  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  public void deliverMessage(String from, String to, String text) {
    Message msg = new Message(from, to, text);
    messageService.deliverMessage(msg);
  }
}
