package com.example.userservice.respositories;

import com.example.userservice.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findByToken(String token);

    Session findByTokenAndUser_Id(String token, Long userId);
}
