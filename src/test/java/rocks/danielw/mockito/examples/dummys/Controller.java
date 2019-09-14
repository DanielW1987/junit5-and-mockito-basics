package rocks.danielw.mockito.examples.dummys;

import java.util.List;

public class Controller {

  private final Service service;

  public Controller(Service service) {
    this.service = service;
  }

  public String proceed(String lastName) {
    List<String> names = service.findAllByLastName("%" + lastName + "%");

    if (names.isEmpty()) {
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

}
