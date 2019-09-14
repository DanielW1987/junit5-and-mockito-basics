package rocks.danielw.message;

import java.util.Objects;

public class Message {

  private String from;
  private String to;
  private String text;

  Message(String from, String to, String text) {
    this.from = from;
    this.to = to;
    this.text = text;
  }

  public String getText() {
    return text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Message message = (Message) o;
    return from.equals(message.from) &&
        to.equals(message.to) &&
        text.equals(message.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, to, text);
  }
}
