package com.example.integradorIVa.TaskMasters.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.integradorIVa.TaskMasters.entity.Tasks;
import com.example.integradorIVa.TaskMasters.entity.User;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
    List<Tasks> findByUser(User user);

}
