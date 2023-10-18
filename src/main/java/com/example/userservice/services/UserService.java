package com.example.userservice.services;

import com.example.userservice.dtos.UserDto;
import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import com.example.userservice.respositories.RoleRepository;
import com.example.userservice.respositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserDto getUserDetails(Long userid) {
        Optional<User> user = userRepository.findById(userid);
        if (user.isPresent()) {
            UserDto userDto = new UserDto();
            userDto.setEmail(user.get().getEmail());
            userDto.setName(user.get().getName());
            userDto.setRoleSet(user.get().getRoleSet());
            return userDto;
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public UserDto setUserRoles(Long userId, List<Long> roleIds) {
        Optional<User> user = userRepository.findById(userId);
        List<Role> roles = roleRepository.findAllByIdIn(roleIds);
        if (user.isPresent()) {
            roles.forEach(role -> System.out.println(user.get().getRoleSet().add(role)));
        } else {
            throw new RuntimeException("User not found");
        }
        return UserDto.from(userRepository.save(user.get()));
    }
}
