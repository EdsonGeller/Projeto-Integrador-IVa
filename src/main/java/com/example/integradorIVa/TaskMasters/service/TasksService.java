package com.example.integradorIVa.TaskMasters.service;

import java.time.Duration;
import static com.example.integradorIVa.TaskMasters.service.DurationConverter.convertToDuration;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.integradorIVa.TaskMasters.entity.Category;
import com.example.integradorIVa.TaskMasters.entity.Tasks;
import com.example.integradorIVa.TaskMasters.entity.User;
import com.example.integradorIVa.TaskMasters.exception.EntityNotFoundException;
import com.example.integradorIVa.TaskMasters.repository.CategoryRepository;
import com.example.integradorIVa.TaskMasters.repository.TasksRepository;
import com.example.integradorIVa.TaskMasters.web.dto.mapper.UserMapper;
import com.example.integradorIVa.TaskMasters.web.dto.taskDto.TaskCreateDto;
import com.example.integradorIVa.TaskMasters.web.dto.taskDto.TaskEditDto;
import com.example.integradorIVa.TaskMasters.web.dto.taskDto.TaskResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TasksService {

    private final LoginContext loginContext;

    private final TasksRepository tasksRepository;

    private final CategoryRepository categoryRepository;

    private final TaskEventPublisher taskEventPublisher;

    @Transactional
    public Tasks createTask(TaskCreateDto createDto) {
        User user = loginContext.getAuthenticatedUser();
        Category category = categoryRepository.findByCategoryNameAndUser(createDto.getCategoryName(), user)
                .orElseThrow(() -> new RuntimeException("task name not found"));

        Tasks tasks = UserMapper.toTasks(createDto);
        tasks.setUser(user);
        tasks.setCategory(category);
        tasks.setPriority(createDto.getPriority());

        Duration duration = convertToDuration(createDto.getDurationDays(), createDto.getDurationHours(), createDto.getDurationMinutes());
        tasks.setDuration(duration);

        return tasksRepository.save(tasks);
    }

    @Transactional(readOnly = true)
    public Tasks findById(Long taskId) {
        return tasksRepository.findById(taskId).orElseThrow(
            () -> new EntityNotFoundException(String.format("Usuario id=%s não encontrado", taskId))
        );
    }
    
    @Transactional(readOnly = true)
    public List<TaskResponseDto> findAll(){
        User user = loginContext.getAuthenticatedUser();

        List<Tasks> tasksList = tasksRepository.findByUser(user);
        return UserMapper.toListDtoTasks(tasksList);
    }

    @Transactional
    public void deleteTask(Long taskId){
        User user = loginContext.getAuthenticatedUser();

        Tasks tasks = tasksRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Id not found"));
        
        if (!tasks.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Você não tem permissão para excluir esta tarefa");
        }
        tasksRepository.delete(tasks);
    }
    
    @Transactional
    public Tasks editTask(Long taskId, TaskEditDto editDto) {
        User user = loginContext.getAuthenticatedUser();
        Category category = categoryRepository.findByCategoryNameAndUser(editDto.getCategoryName(), user)
                .orElseThrow(() -> new RuntimeException("task name not found"));

        Tasks tasks = findById(taskId);
        tasks.setUser(user);
        tasks.setCategory(category);
        tasks.setTaskName(editDto.getTaskName());
        tasks.setPriority(editDto.getPriority());

        return tasksRepository.save(tasks);
    }

    @Transactional
    public Tasks starTasks(Long taskId){
        User user = loginContext.getAuthenticatedUser();
        Tasks task = findById(taskId);
        task.setUser(user);
        task.startTask();
        tasksRepository.save(task);
        taskEventPublisher.publishTaskEvent(task);
        return task;

    }

    @Transactional
    public Tasks finishTask(Long taskId){
        User user = loginContext.getAuthenticatedUser();
        Tasks task = findById(taskId);
        task.setUser(user);
        task.finishTask();
        return tasksRepository.save(task);
    }
    
}
