
package cl.bci.ms.user.repository;

import cl.bci.ms.user.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * UserRepository.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 11-09-2023
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = "SELECT CASE WHEN count(u)> 0 then true else false end from User u where lower(u.name) like lower(:name) and lower(u.email) like lower(:email)",nativeQuery = false)
    boolean existsUserByNameByEmail(@Param("name") String name, @Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.token =:token")
    User findUserByUserToken(@Param("token") String token);

    Optional<User> findUserByName(Object name);

    @Modifying(clearAutomatically=true)
    @Transactional
    @Query(value = "update User u set u.token =:newToken where u.token =:oldToken")
    User updateUserByToken(@Param("oldToken")String oldToken,@Param("newToken")String newToken);

}
