package np.com.esewa.learn.springsecuritysample.repository;

import np.com.esewa.learn.springsecuritysample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Thinkpad on 5/2/2023
 * @project SpringSecuritySample
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    User findUserByUsername(String username);
}
