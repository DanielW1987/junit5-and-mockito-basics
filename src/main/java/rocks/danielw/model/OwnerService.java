package rocks.danielw.model;

import java.util.List;
import java.util.Set;

public interface OwnerService {

  Set<Owner> findAll();

  Owner findById(Long id);

  Owner findByLastName(String lastName);

  List<Owner> findAllByLastNameLike(String lastName);

  Owner save(Owner owner);

  void delete(Owner owner);

  void deleteById(Long id);

}
