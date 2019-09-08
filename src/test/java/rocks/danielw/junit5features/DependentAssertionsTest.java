package rocks.danielw.junit5features;

import org.junit.jupiter.api.Test;
import rocks.danielw.model.Owner;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DependentAssertionsTest {

  @Test
  void dependentAssertions() {
    Owner owner = Owner.builder().id(1L).firstName("John").lastName("Doe").build();
    owner.setCity("New York");
    owner.setTelephone("123456987");

    assertAll("Properties Test",
            () -> assertAll("Name properties",
                    () -> assertEquals("John", owner.getFirstName(), "First name does not match"),
                    () -> assertEquals("Doe", owner.getLastName(), "Last name does not match")),
            () -> assertAll("Contact properties",
                    () -> assertEquals("New York", owner.getCity(), "City does not match"),
                    () -> assertEquals("123456987", owner.getTelephone(), "Telephone does not match")
            ));
  }

}
