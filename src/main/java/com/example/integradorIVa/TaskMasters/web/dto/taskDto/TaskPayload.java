package com.example.integradorIVa.TaskMasters.web.dto.taskDto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskPayload {

    private Long taskId;
    private Long userId;
    private LocalDateTime startDate;
    private String duration;
    private LocalDateTime finishDate;
}
