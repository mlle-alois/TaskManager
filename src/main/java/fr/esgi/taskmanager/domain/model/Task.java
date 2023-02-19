package fr.esgi.taskmanager.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
    private TaskId id;
    private String content;
    private LocalDate dueDate;
    private TaskStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;

    public Task(String content, LocalDate dueDate){
        this.content = content;
        this.dueDate = dueDate;
        this.status = TaskStatus.TODO;
        this.creationDate = LocalDateTime.now();
    }

    public Task(TaskId id, String content, LocalDate dueDate, TaskStatus status, LocalDateTime creationDate) {
        this.id = id;
        this.content = content;
        this.dueDate = dueDate;
        this.status = status;
        this.creationDate = creationDate;
    }

    public boolean isOverdue() {
        return dueDate.isBefore(LocalDate.now());
    }

    public void setContent(String content) {
        this.content = content;
        this.modificationDate = LocalDateTime.now();
    }
    public void setId(TaskId id) {
        this.id = id;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        this.modificationDate = LocalDateTime.now();
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
        this.modificationDate = LocalDateTime.now();
    }

    public TaskId getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                ", content='" + content + '\'' +
                ", dueDate=" + dueDate +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                '}';
    }
}