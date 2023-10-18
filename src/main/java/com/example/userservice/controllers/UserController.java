package com.example.userservice.controllers;

import com.example.userservice.dtos.SetUserRolesRequestDto;
import com.example.userservice.dtos.UserDto;
import com.example.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable("id") Long userId) {
        UserDto userDto = userService.getUserDetails(userId);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("{id}/roles")
    public ResponseEntity<UserDto> setUserRoles(@PathVariable("id") Long userId, @RequestBody SetUserRolesRequestDto setUserRolesRequestDto) {
        UserDto userDto = userService.setUserRoles(userId, setUserRolesRequestDto.getRoleIds());
        return ResponseEntity.ok(userDto);
    }

}
