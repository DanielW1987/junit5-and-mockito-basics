package rocks.danielw.mockito.examples;

import org.assertj.core.api.WithAssertions;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static rocks.danielw.mockito.examples.CustomMatchers.actionEventEq;

class CustomArgumentMatcher implements WithMockito, WithAssertions {

  @Mock
  private EventProducer eventProducer;

  @InjectMocks
  private MyService service;

  /*
   * Creating our own matcher can be good to select the best possible approach for a given scenario and produce highest quality test, which is clean and maintainable.
   * For instance, we could have a service that create events via a event producer. The event producer receives an event and publishes it.
   * Our verification will be simple, verify that we called the event producer exactly 1 time with any event:
   *
   *    verify(eventProducer, times(1)).fireEvent(any(ActionEvent.class));
   *
   * Because the ActionEvent is constructed inside the method under test, we’re forced to use any() as the matcher.
   * This approach doesn't let us validate the data inside the ActionEvent, which can be useful.
   * For that reason, we're going to implement a custom argument matcher.
   */

  @Test
  void testCustomArgumentMatcher() {
    service.doSomething();

    // verify that MyService is called with an ActionEvent that has certain values
    Object expectedSource = "Source";
    verify(eventProducer, times(1)).fireEvent(actionEventEq(expectedSource, "EDIT"));

  }

}

class CustomMatchers {

  static ActionEvent actionEventEq(Object expectedSource, String actionCommand) {
    return argThat(new ActionEventMatcher(equalToObject(expectedSource), any(Integer.class), equalTo(actionCommand)));
  }

}

class ActionEventMatcher implements ArgumentMatcher<ActionEvent> {

  // use hamcrest Matcher for that
  private Matcher<Object> sourceMatcher;
  private Matcher<Integer> idMatcher;
  private Matcher<String> actionCommandMatcher;

  ActionEventMatcher(Matcher<Object> sourceMatcher, Matcher<Integer> idMatcher, Matcher<String> actionCommandMatcher) {
    this.sourceMatcher = sourceMatcher;
    this.idMatcher = idMatcher;
    this.actionCommandMatcher = actionCommandMatcher;
  }

  @Override
  public boolean matches(ActionEvent actionEvent) {
    return this.sourceMatcher.matches(actionEvent.getSource())
            && this.idMatcher.matches(actionEvent.getId())
            && this.actionCommandMatcher.matches(actionEvent.getActionCommand());
  }
}

class ActionEvent{

  private Object source;
  private int id;
  private String actionCommand;

  ActionEvent(Object source, int id, String actionCommand) {
    this.source = source;
    this.id = id;
    this.actionCommand = actionCommand;
  }

  Object getSource() {
    return source;
  }

  int getId() {
    return id;
  }

  String getActionCommand() {
    return actionCommand;
  }

}

class MyService {

  private final EventProducer eventProducer;

  MyService(EventProducer eventProducer) {
    this.eventProducer = eventProducer;
  }

  void doSomething() {
    eventProducer.fireEvent(new ActionEvent("Source", 99, "EDIT"));
  }

}

class EventProducer {

  void fireEvent(ActionEvent event){
    // fire...
  }

}
