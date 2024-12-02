package com.example.integradorIVa.TaskMasters.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.integradorIVa.TaskMasters.entity.Category;
import com.example.integradorIVa.TaskMasters.service.CategoryService;
import com.example.integradorIVa.TaskMasters.web.dto.categoryDto.CategoryCreateDto;
import com.example.integradorIVa.TaskMasters.web.dto.categoryDto.CategoryEditDto;
import com.example.integradorIVa.TaskMasters.web.dto.categoryDto.CategoryResponseDto;
import com.example.integradorIVa.TaskMasters.web.dto.mapper.UserMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/categories")

public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@Valid @RequestBody CategoryCreateDto categoryCreateDto) {
        Category category = categoryService.createCategory(categoryCreateDto);
        CategoryResponseDto responseDto = UserMapper.toDtoCategory(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategory() {
        List<CategoryResponseDto> list = categoryService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDto> findById(@PathVariable Long categoryId) {
        Category category = categoryService.findById(categoryId);
        return ResponseEntity.ok(UserMapper.toDtoCategory(category));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Void> editCategory(@Valid  @PathVariable Long categoryId, @RequestBody CategoryEditDto editDto) {
        categoryService.editCategory(categoryId, editDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok().build();
    }
}
