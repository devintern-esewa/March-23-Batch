package np.com.esewa.learn.springsecuritysample.controller;

import np.com.esewa.learn.springsecuritysample.entity.enums.UserRole;
import np.com.esewa.learn.springsecuritysample.service.role.RoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SatyaRajAwasth1 on 5/5/2023
 * Controller class for Roles SpringSecuritySample
 */

@RestController
//@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/api/roles/add")
    public void addRole(@RequestBody UserRole userRole){
        roleService.addRole(userRole);
    }
}
