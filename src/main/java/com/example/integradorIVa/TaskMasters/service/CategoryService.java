package com.example.integradorIVa.TaskMasters.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.integradorIVa.TaskMasters.entity.Category;
import com.example.integradorIVa.TaskMasters.entity.User;
import com.example.integradorIVa.TaskMasters.repository.CategoryRepository;
import com.example.integradorIVa.TaskMasters.web.dto.categoryDto.CategoryCreateDto;
import com.example.integradorIVa.TaskMasters.web.dto.categoryDto.CategoryEditDto;
import com.example.integradorIVa.TaskMasters.web.dto.categoryDto.CategoryResponseDto;
import com.example.integradorIVa.TaskMasters.web.dto.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final LoginContext loginContext;


    @Transactional
    public Category createCategory(CategoryCreateDto createDto){
        User user = loginContext.getAuthenticatedUser();

        Category category = UserMapper.toCategory(createDto);
        category.setUser(user);

        return categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public Category findById(Long categoryId){
        User user = loginContext.getAuthenticatedUser();

        Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new RuntimeException("Id not found"));
        if (!category.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Você não tem permissão para editar esta categoria");
        }

        return category;
    }

    @Transactional(readOnly = true)
    public List<CategoryResponseDto> findAll(){
        User user = loginContext.getAuthenticatedUser();

        List<Category> categoryList = categoryRepository.findByUser(user);
        return UserMapper.toListDtoCategory(categoryList);
    }
    
    @Transactional
    public Category editCategory(Long categoryId, CategoryEditDto editDto){
        User user = loginContext.getAuthenticatedUser();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Id not found"));
        if (!category.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Você não tem permissão para editar esta categoria");
        }

        category.setCategoryName(editDto.getCategoryName());

        return categoryRepository.save(category);
    }
    
    @Transactional
    public void deleteCategory(Long categoryId){
        User user = loginContext.getAuthenticatedUser();

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Id not found"));
        
        if (!category.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Você não tem permissão para excluir esta categoria");
        }
        categoryRepository.delete(category);
    }
}
