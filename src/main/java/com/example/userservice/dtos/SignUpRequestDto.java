package com.example.userservice.dtos;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
