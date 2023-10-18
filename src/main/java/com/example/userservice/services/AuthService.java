package com.example.userservice.services;

import com.example.userservice.dtos.*;
import com.example.userservice.models.Session;
import com.example.userservice.models.SessionStatus;
import com.example.userservice.models.User;
import com.example.userservice.respositories.UserRepository;
import com.example.userservice.respositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final SessionRepository sessionRepository;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    public UserDto signUp(SignUpRequestDto signUpRequestDto) {
        User user = new User();
        user.setName(signUpRequestDto.getUsername());
        user.setEmail(signUpRequestDto.getEmail());
        user.setPassword(signUpRequestDto.getPassword());
        userRepository.save(user);
        return UserDto.from(user);
    }

    public UserDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.getEmail());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!user.getPassword().equals(loginRequestDto.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        Session session = new Session();
        session.setUser(user);
        session.setToken("token");
        session.setExpiresAt(Date.from(new Date().toInstant().plusSeconds(3600)));
        session.setSessionStatus(SessionStatus.ACTIVE);
        sessionRepository.save(session);
        return UserDto.from(user);
    }

    public void logout(LogoutRequestDto logoutRequestDto) {
        Session session = sessionRepository.findByToken(logoutRequestDto.getToken());
        if (session == null) {
            throw new RuntimeException("Session not found");
        }
        session.setSessionStatus(SessionStatus.INACTIVE);
        sessionRepository.save(session);
    }

    public SessionStatus validateToken(String token, Long userId) {
        Session session = sessionRepository.findByTokenAndUser_Id(token, userId);
        if (session == null) {
            throw new RuntimeException("Session not found");
        }
        if (session.getSessionStatus() == SessionStatus.INACTIVE) {
            throw new RuntimeException("Session is inactive");
        }
        if (session.getUser().getId() != userId) {
            throw new RuntimeException("Invalid user");
        }
        return SessionStatus.ACTIVE;
    }
}
