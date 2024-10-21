package com.itMentor.Task312.repositories;

import com.itMentor.Task312.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    Optional<UserAuth> findByUsername(String username);
}
