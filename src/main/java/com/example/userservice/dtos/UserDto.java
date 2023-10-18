package com.example.userservice.dtos;

import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String email;

    private String name;
    private Set<Role> roleSet = new HashSet<>();

    public static UserDto from(User user) {
        return new UserDto(user.getEmail(),user.getName(), user.getRoleSet());
    }
}
