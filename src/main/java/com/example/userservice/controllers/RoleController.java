package com.example.userservice.controllers;

import com.example.userservice.dtos.CreateRoleRequestDto;
import com.example.userservice.models.Role;
import com.example.userservice.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody CreateRoleRequestDto createRoleRequestDto) {
        Role role = roleService.createRole(createRoleRequestDto);
        return ResponseEntity.ok(role);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleDetails(@PathVariable("id") Long roleId) {
        Role role = roleService.getRoleDetails(roleId);
        return ResponseEntity.ok(role);
    }
}
