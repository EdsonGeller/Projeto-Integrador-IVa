package com.example.integradorIVa.TaskMasters.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.integradorIVa.TaskMasters.entity.User;
import com.example.integradorIVa.TaskMasters.exception.EntityNotFoundException;
import com.example.integradorIVa.TaskMasters.exception.PasswordInvalidException;
import com.example.integradorIVa.TaskMasters.exception.UsernameUniqueViolationException;
import com.example.integradorIVa.TaskMasters.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch(DataIntegrityViolationException ex){
            throw new UsernameUniqueViolationException(String.format("Username '%s' já cadastrado", user.getUsername()));
        }
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(String.format("Usuario id=%s não encontrado", id))
        );
    }
    
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuario id=%s não encontrado", username)));
    }

    @Transactional
    public User changePassword(Long id, String currentlyPassword, String newPassword, String checkPassword){
        if (!newPassword.equals(checkPassword)) {
            throw new PasswordInvalidException("Nova senha não confere com confirmação de senha.");
        }
        User user = findById(id);
        if (!passwordEncoder.matches(currentlyPassword, user.getPassword())) {
            throw new PasswordInvalidException("Sua senha não confere.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        return user;
    }

    
    
}
    
