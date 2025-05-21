package me.github.IanGFreitas.entities;

import java.time.LocalDate;
import java.util.Calendar;

public class Task {
    private Integer id;
    private String description;
    private Status status;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Task(int id, String description, Status status){
        this.id = id;
        this.description = description;
        this.status = status;
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
    }

    public Task(int id, String description, Status status, LocalDate createdAt, LocalDate updatedAt){
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId(){
        return id;
    }

    public String getDescription(){
        return description;
    }

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public LocalDate createdAt(){
        return createdAt;
    }

    public LocalDate updatedAt(){
        return updatedAt;
    }

    public void updateTask(String newDescription){
        description = newDescription;
        updatedAt = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
