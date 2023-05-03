package com.airline.airlineticketing.controller;

import com.airline.airlineticketing.model.Role;
import com.airline.airlineticketing.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> getAll(){
        return roleService.getAllRoles();
    }
    @GetMapping("/{id}")
    public Role findById(@PathVariable int id){
        return roleService.findById(id);
    }

    @PostMapping
    public Role addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    @PutMapping
    public Role updateRole(@RequestBody Role role){
        return roleService.updateRole(role);
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable int id){
        return roleService.deleteRole(id);
    }
}
