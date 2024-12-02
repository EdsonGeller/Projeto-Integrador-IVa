package com.example.integradorIVa.TaskMasters.entity;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
public class Tasks implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;
    
    @Column(name = "task_name")
    private String taskName;

    @CreatedDate
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "start_date", nullable = true)
    private LocalDateTime startDate;

    private Boolean isStarted = false;

    @Column(name = "finish_date", nullable = true)
    private LocalDateTime finishDate;

    private Boolean isFinished = false;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name="priority", nullable = false)
    private Priority priority;

    @Column(name = "duration", nullable = false)
    private Duration duration;

    public enum Priority{
        NORMAL, IMPORTANTE, PRIORIDADE, URGENTE
    }

    public void startTask(){
        if (!this.isStarted) {
            this.isStarted = true;
            this.startDate = LocalDateTime.now();
        }
    }
    
    public void finishTask(){
        if (!this.isFinished) {
            this.isFinished = true;
            this.finishDate = LocalDateTime.now();
        }
    }
    
}
