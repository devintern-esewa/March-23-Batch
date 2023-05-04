package np.com.esewa.learn.springsecuritysample.repository;

import np.com.esewa.learn.springsecuritysample.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Thinkpad on 5/2/2023
 * @project SpringSecuritySample
 */
public interface RoleRepository extends JpaRepository<Role, Byte> {
}
