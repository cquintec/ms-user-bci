
package cl.bci.ms.user.repository;

import cl.bci.ms.user.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PhoneRepository.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 12-09-2023
 */
@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
