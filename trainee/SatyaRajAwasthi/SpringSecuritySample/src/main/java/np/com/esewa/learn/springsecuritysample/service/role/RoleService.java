package np.com.esewa.learn.springsecuritysample.service.role;

import np.com.esewa.learn.springsecuritysample.entity.enums.UserRole;

/**
 * @author Thinkpad on 5/2/2023
 *  service for role
 */
public interface RoleService {
    public boolean addRole(UserRole userRole);
}
