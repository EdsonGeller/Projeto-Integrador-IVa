package com.example.integradorIVa.TaskMasters.web.dto.taskDto;

import java.time.Duration;

import com.example.integradorIVa.TaskMasters.entity.Tasks.Priority;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskEditDto {
    @NotBlank
    @Size(min = 1, max = 20)
    private String taskName;

    @NotBlank
    private String categoryName;
    
    @NotBlank
    private Priority priority;
    
    @NotBlank
    private Duration duration;
}
