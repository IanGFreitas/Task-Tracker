package me.github.IanGFreitas.entities;

import java.util.Calendar;

public class Task {
    private Integer id;
    private String description;
    private Status status;
    private Calendar createdAt;
    private Calendar updatedAt;

    public Task(int id, String description, Status status){
        this.id = id;
        this.description = description;
        this.status = status;
        createdAt = Calendar.getInstance();
        updatedAt = Calendar.getInstance();
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

    public Calendar createdAt(){
        return createdAt;
    }

    public Calendar updatedAt(){
        return updatedAt;
    }

    public void updateTask(String newDescription){
        description = newDescription;
        updatedAt = Calendar.getInstance();
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
