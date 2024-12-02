package com.example.integradorIVa.TaskMasters.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.integradorIVa.TaskMasters.entity.User;
import com.example.integradorIVa.TaskMasters.repository.UserRepository;

@Component
public class LoginContext {

    private final UserRepository userRepository;

    public LoginContext(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
