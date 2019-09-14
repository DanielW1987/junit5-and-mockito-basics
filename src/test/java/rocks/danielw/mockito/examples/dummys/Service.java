package rocks.danielw.mockito.examples.dummys;

import java.util.List;

public class Service {

  public List<String> findAllByLastName(String lastName) {
    return List.of();
  }

  public void varArgMethod(Person... persons) {
    // do something with persons...
  }

}
