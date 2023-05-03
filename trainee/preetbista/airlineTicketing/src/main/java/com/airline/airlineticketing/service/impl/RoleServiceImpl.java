package com.airline.airlineticketing.service.impl;

import com.airline.airlineticketing.exception.RoleNotFoundException;
import com.airline.airlineticketing.model.Role;
import com.airline.airlineticketing.repository.RoleRepository;
import com.airline.airlineticketing.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(int id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        return optionalRole
                .orElseThrow(() -> new RoleNotFoundException("Role not found for id :"+id));
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        findById(role.getId());
        return roleRepository.save(role);
    }

    @Override
    public String deleteRole(int id) {
        findById(id);
        roleRepository.deleteById(id);
        return "Role deleted successfully";
    }

}
