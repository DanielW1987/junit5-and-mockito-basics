package rocks.danielw.junit5features;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import rocks.danielw.model.Owner;
import rocks.danielw.model.OwnerService;
import rocks.danielw.model.OwnerServiceImpl;

@Disabled("We can also disable the whole test class") // doesn't work if you execute your tests via IntelliJ
class DisabledTest {

  private OwnerService service;

  @BeforeEach
  void setup() {
    service = new OwnerServiceImpl();
  }

  @Test
  @Disabled("test is not going to be executed")
  void findByLastName() {
    Owner owner = service.findByLastName("Doe"); // will throw exception
  }

  @Test
  void findAllByLastNameLike() {
    System.out.println("is not going to be executed, because the whole test class is disabled");
  }

  @Test
  void findAll() {
    System.out.println("is not going to be executed, because the whole test class is disabled");
  }

  @Test
  void findById() {
    System.out.println("is not going to be executed, because the whole test class is disabled");
  }

  @Test
  void save() {
    System.out.println("is not going to be executed, because the whole test class is disabled");
  }

}
