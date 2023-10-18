package com.example.userservice.services;

import com.example.userservice.dtos.CreateRoleRequestDto;
import com.example.userservice.models.Role;
import com.example.userservice.respositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(CreateRoleRequestDto createRoleRequestDto) {
        Role role = new Role();
        role.setRole(createRoleRequestDto.getRole());
        return roleRepository.save(role);
    }

    public Role getRoleDetails(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
    }

}
