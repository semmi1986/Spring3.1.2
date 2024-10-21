package com.itMentor.Task312.service;

import com.itMentor.Task312.model.UserAuth;
import com.itMentor.Task312.repositories.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
public class UserAuthDetailServiceImpl implements UserDetailsService {

    private final UserAuthRepository repository;

    @Autowired
    public UserAuthDetailServiceImpl(UserAuthRepository userAuthRepository) {
        this.repository = userAuthRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<UserAuth> user = repository.findByUsername(username);
       if (user.isPresent()) {
           var userAuthInfo = user.get();
           return User.builder()
                   .username(userAuthInfo.getUsername())
                   .password(userAuthInfo.getPassword())
                   .roles(getRoles(userAuthInfo))
                   .build();
       }else {
           throw new UsernameNotFoundException(username);
       }
    }

    private String[] getRoles(UserAuth user){
        if (user.getRole() == null){
            return new String[]{"USER"};
        }
        return user.getRole().split(", ");
    }
}
