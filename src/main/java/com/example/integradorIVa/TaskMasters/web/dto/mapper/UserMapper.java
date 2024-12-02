package com.example.integradorIVa.TaskMasters.web.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.example.integradorIVa.TaskMasters.entity.Category;
import com.example.integradorIVa.TaskMasters.entity.Tasks;
import com.example.integradorIVa.TaskMasters.entity.User;
import com.example.integradorIVa.TaskMasters.web.dto.categoryDto.CategoryCreateDto;
import com.example.integradorIVa.TaskMasters.web.dto.categoryDto.CategoryResponseDto;
import com.example.integradorIVa.TaskMasters.web.dto.taskDto.TaskCreateDto;
import com.example.integradorIVa.TaskMasters.web.dto.taskDto.TaskResponseDto;
import com.example.integradorIVa.TaskMasters.web.dto.userDto.UserCreateDto;
import com.example.integradorIVa.TaskMasters.web.dto.userDto.UserResponseDto;

public class UserMapper {

    public static User toUser(UserCreateDto createDto){
        return new ModelMapper().map(createDto, User.class);
    }

    public static UserResponseDto toDto(User user){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(user, UserResponseDto.class);
    }

    public static List<UserResponseDto> toListDto(List<User> users){
        return users.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }
    
    
    public static Category toCategory(CategoryCreateDto createDto){
        return new ModelMapper().map(createDto, Category.class);
    }

    public static CategoryResponseDto toDtoCategory(Category category ){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(category, CategoryResponseDto.class);
    }

    public static List<CategoryResponseDto> toListDtoCategory(List<Category> categories){
        return categories.stream().map(UserMapper::toDtoCategory).collect(Collectors.toList());
    }
    
    public static Tasks toTasks(TaskCreateDto createDto){
        return new ModelMapper().map(createDto, Tasks.class);
    }

    public static TaskResponseDto toDtoTasks(Tasks tasks ){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(tasks, TaskResponseDto.class);
    }

    public static List<TaskResponseDto> toListDtoTasks(List<Tasks> tasks){
        return tasks.stream().map(UserMapper::toDtoTasks).collect(Collectors.toList());
    }
}
