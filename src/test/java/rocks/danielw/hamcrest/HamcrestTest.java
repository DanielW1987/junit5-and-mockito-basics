package rocks.danielw.hamcrest;

import org.junit.jupiter.api.Test;
import rocks.danielw.model.Owner;

import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * documentation: http://hamcrest.org/JavaHamcrest/
 */
class HamcrestTest {

  @Test
  void simpleAssertJTest() {
    Owner owner = Owner.builder().id(1L).firstName("John").lastName("Doe").build();
    owner.setCity("New York");
    owner.setTelephone("123456987");

    assertThat(owner.getFirstName(), equalTo("John"));
    assertThat(owner.getLastName(), equalTo("Doe"));
    assertThat(owner.getTelephone(), matchesPattern(Pattern.compile("^[0-9]*$")));
  }

}
