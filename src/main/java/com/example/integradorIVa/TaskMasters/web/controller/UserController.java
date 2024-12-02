package com.example.integradorIVa.TaskMasters.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.integradorIVa.TaskMasters.entity.User;
import com.example.integradorIVa.TaskMasters.service.UserService;
import com.example.integradorIVa.TaskMasters.web.dto.mapper.UserMapper;
import com.example.integradorIVa.TaskMasters.web.dto.userDto.UserCreateDto;
import com.example.integradorIVa.TaskMasters.web.dto.userDto.UserPasswordDto;
import com.example.integradorIVa.TaskMasters.web.dto.userDto.UserResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateDto createDto) {
        User user = userService.createUser(UserMapper.toUser(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
    };

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(UserMapper.toDto(user));
    };

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UserPasswordDto dto) {
        userService.changePassword(id, dto.getCurrentlyPassword(), dto.getNewPassword(), dto.getCheckPassword());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
