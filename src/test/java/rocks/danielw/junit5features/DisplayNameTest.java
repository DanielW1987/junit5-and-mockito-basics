package rocks.danielw.junit5features;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DisplayNameTest {

  @Test
  @DisplayName("Nice test description")
  void findAllByLastNameLike() {
  }

  @Test
  @DisplayName("Find all resources")
  void findAll() {
  }

  @Test
  @DisplayName("Find resource by Id")
  void findById() {
  }

}
