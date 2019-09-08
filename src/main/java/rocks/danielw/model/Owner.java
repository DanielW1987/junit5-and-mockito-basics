package rocks.danielw.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Owner {

  private Long id;

  @NonNull
  private String firstName;

  @NonNull
  private String lastName;

  private String address;
  private String city;
  private String telephone;

}
