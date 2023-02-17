package fr.esgi.taskManager.domain.service;

import fr.esgi.taskManager.domain.model.Task;
import fr.esgi.taskManager.domain.model.TaskStatus;

import java.time.LocalDate;

public interface TaskCommandService {
    Task addTask(String content, LocalDate dueDate);
    void removeTask(int id);
    Task updateTask(int id, String content, LocalDate dueDate, TaskStatus status);
}