package com.example.integradorIVa.TaskMasters.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.integradorIVa.TaskMasters.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String username);
}
