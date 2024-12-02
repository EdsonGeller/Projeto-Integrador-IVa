package com.example.integradorIVa.TaskMasters.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.integradorIVa.TaskMasters.entity.Tasks;
import com.example.integradorIVa.TaskMasters.service.TasksService;
import com.example.integradorIVa.TaskMasters.web.dto.mapper.UserMapper;
import com.example.integradorIVa.TaskMasters.web.dto.taskDto.TaskCreateDto;
import com.example.integradorIVa.TaskMasters.web.dto.taskDto.TaskEditDto;
import com.example.integradorIVa.TaskMasters.web.dto.taskDto.TaskResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/tasks")
public class TasksController {

    private final TasksService tasksService;

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody TaskCreateDto taskCreateDto) {
        Tasks tasks = tasksService.createTask(taskCreateDto);
        TaskResponseDto responseDto = UserMapper.toDtoTasks(tasks);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        List<TaskResponseDto> tasks = tasksService.findAll();
        return ResponseEntity.ok(tasks);
    }
    
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        tasksService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Void> editTask(@Valid @PathVariable Long taskId, @RequestBody TaskEditDto editDto) {
        tasksService.editTask(taskId, editDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponseDto> findTaskById(@PathVariable Long taskId) {
        Tasks task = tasksService.findById(taskId);
        return ResponseEntity.ok(UserMapper.toDtoTasks(task));
    }
    
    @PatchMapping("/{taskId}/start")
    public ResponseEntity<Void> startTask(@PathVariable Long taskId){
        tasksService.starTasks(taskId);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{taskId}/finish")
    public ResponseEntity<Void> finishTask(@PathVariable Long taskId){
        tasksService.finishTask(taskId);
        return ResponseEntity.noContent().build();
    }
}
