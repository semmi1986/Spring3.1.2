package com.itMentor.Task312.controller;

import com.itMentor.Task312.model.UserAuth;
import com.itMentor.Task312.repositories.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public UserAuth createUser(@RequestBody UserAuth user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userAuthRepository.save(user);
    }

}
