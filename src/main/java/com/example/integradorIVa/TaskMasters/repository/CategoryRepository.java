package com.example.integradorIVa.TaskMasters.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.integradorIVa.TaskMasters.entity.Category;
import com.example.integradorIVa.TaskMasters.entity.User;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
    Optional<Category> findByCategoryNameAndUser(String categoryName, User user);
    List<Category> findByUser(User user);

}
