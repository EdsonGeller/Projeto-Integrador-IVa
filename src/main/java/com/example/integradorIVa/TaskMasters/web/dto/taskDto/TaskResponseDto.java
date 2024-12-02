package com.example.integradorIVa.TaskMasters.web.dto.taskDto;

import java.time.Duration;
import java.time.LocalDateTime;

import com.example.integradorIVa.TaskMasters.entity.Tasks.Priority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponseDto {

    private Long taskId;
    private String taskName;
    private LocalDateTime creationDate;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private String categoryName;
    private Long userId;
    private Priority priority;
    private Duration duration;
}
