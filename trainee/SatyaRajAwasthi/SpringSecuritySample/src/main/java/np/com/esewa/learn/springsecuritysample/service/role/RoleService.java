package np.com.esewa.learn.springsecuritysample.service.role;

import np.com.esewa.learn.springsecuritysample.entity.enums.UserRole;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author Thinkpad on 5/2/2023
 *  service for role
 */
public interface RoleService {
    @PreAuthorize("hasRole('ADMIN')")
    public boolean addRole(UserRole userRole);
}
