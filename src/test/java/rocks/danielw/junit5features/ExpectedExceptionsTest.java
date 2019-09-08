package rocks.danielw.junit5features;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rocks.danielw.model.OwnerService;
import rocks.danielw.model.OwnerServiceImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpectedExceptionsTest {

  private OwnerService service;

  @BeforeEach
  void setup() {
    service = new OwnerServiceImpl();
  }

  @Test
  void expectException() {
    assertThrows(UnsupportedOperationException.class, () -> service.findAll());
  }

}
