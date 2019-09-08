package rocks.danielw.junit5features;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import rocks.danielw.model.Owner;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupAssertionsTest {

  @Test
  void groupAssertions() {
    // given
    Owner owner = Owner.builder().id(1L).firstName("John").lastName("Doe").build();

    // then
    assertAll("Test props set",
            () -> assertEquals("John", owner.getFirstName()),
            () -> assertEquals("Doe", owner.getLastName()));
  }

  @Test
  @Tag("fail")
  void groupAssertionsWithMessages() {
    // given
    Owner owner = Owner.builder().id(1L).firstName("John").lastName("Doe").build();

    // then
    assertAll("Test props set",
            () -> assertEquals("Ernie", owner.getFirstName(), "First name is not equal"),
            () -> assertEquals("Sesam", owner.getLastName(), "Last name is not equal"));
  }

}