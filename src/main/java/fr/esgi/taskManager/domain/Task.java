package fr.esgi.taskManager.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
    private int id;
    private String content;
    private LocalDate dueDate;
    private TaskStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;

    // Constructor and getters

    public boolean isOverdue() {
        return dueDate.isBefore(LocalDate.now());
    }

    public void setContent(String content) {
        this.content = content;
        this.modificationDate = LocalDateTime.now();
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        this.modificationDate = LocalDateTime.now();
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
        this.modificationDate = LocalDateTime.now();
    }

}