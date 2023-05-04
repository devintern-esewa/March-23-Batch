package np.com.esewa.learn.springsecuritysample.service.role;

import np.com.esewa.learn.springsecuritysample.entity.Role;
import np.com.esewa.learn.springsecuritysample.entity.enums.UserRole;
import np.com.esewa.learn.springsecuritysample.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Thinkpad on 5/2/2023
 *  implementation for RoleService
 */
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean addRole(UserRole userRole) {
        Role role = new Role();
        role.setUserRoleName(userRole);
        roleRepository.save(role);
        return true;
    }
}
