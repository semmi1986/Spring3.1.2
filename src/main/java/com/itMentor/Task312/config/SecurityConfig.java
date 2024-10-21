package com.itMentor.Task312.config;

import com.itMentor.Task312.service.UserAuthDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserAuthDetailServiceImpl userAuthDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/home", "/register/**").permitAll();
                    registry.requestMatchers("/admin/**", "/home").hasRole("ADMIN");
                    registry.requestMatchers("/user/**").hasRole("USER");
                    registry.anyRequest().authenticated();
                })
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails normalUser = User.builder()
//                .username("easy")
//                .password("$2a$12$ytCh.AKSv6LJopBJJiyF5eIgkmftc67PhsW2lGMQ03F5CIZHVzT.2") //123
//                .roles("USER")
//                .build();
//        UserDetails adminUser = User.builder()
//                .username("admin")
//                .password("$2a$12$2rPBxYTazBEegCxr/bs2jONvBi1oy9LxPHNBn3mgT7udwapi/ZkQG") //456
//                .roles("ADMIN", "USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(normalUser, adminUser);
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userAuthDetailService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userAuthDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
