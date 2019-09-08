package rocks.danielw.assertj;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import rocks.danielw.model.Owner;

/**
 * Very good documentation: https://assertj.github.io/doc/
 */
class AssertJTest implements WithAssertions {

  @Test
  void simpleAssertJTest() {
    Owner owner = Owner.builder().id(1L).firstName("John").lastName("Doe").build();
    owner.setCity("New York");
    owner.setTelephone("123456987");

    assertThat(owner.getFirstName()).isEqualTo("John");
    assertThat(owner.getLastName()).isEqualTo("Doe");
    assertThat(owner.getTelephone()).containsOnlyDigits();
  }

}
