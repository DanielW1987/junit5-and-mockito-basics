package rocks.danielw.mockito.examples.dummys;

import java.util.List;

public class Controller {

  private final Service service;

  private final OtherService otherService;

  public Controller(Service service, OtherService otherService) {
    this.service = service;
    this.otherService = otherService;
  }

  public String proceed(String lastName) {
    List<String> names = service.findAllByLastName("%" + lastName + "%");

    if (names.isEmpty()) {
      service.doSomething();
      return "view/empty";
    }
    else if (names.size() == 1) {
      return "view/single";
    }
    else {
      return "view/list";
    }
  }

  public void proceedPersons() {
    service.varArgMethod(new Person("John", "Doe"), new Person("Thomas", "Doe"));
  }

  public void executeServices() {
    otherService.doSomething();
    service.doSomething();
  }

}
