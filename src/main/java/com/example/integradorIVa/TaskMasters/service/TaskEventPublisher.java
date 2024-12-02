package com.example.integradorIVa.TaskMasters.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.integradorIVa.TaskMasters.entity.Tasks;
import com.example.integradorIVa.TaskMasters.web.dto.taskDto.TaskPayload;

@Service
public class TaskEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public TaskEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishTaskEvent(Tasks task) {
        try {
            // Criando o objeto TaskPayload
            TaskPayload taskPayload = new TaskPayload();
            taskPayload.setTaskId(task.getTaskId());
            taskPayload.setUserId(task.getUser().getId());
            taskPayload.setStartDate(task.getStartDate());
            taskPayload.setDuration(task.getDuration().toString());
            taskPayload.setFinishDate(task.getFinishDate() != null ? task.getFinishDate() : null);

            // Enviando o objeto TaskPayload como mensagem
            rabbitTemplate.convertAndSend("tasks.exchange", "task.created", taskPayload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}