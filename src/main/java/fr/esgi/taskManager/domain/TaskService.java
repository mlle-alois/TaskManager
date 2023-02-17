package fr.esgi.taskManager.domain;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    List<Task> listTasks();
    Task addTask(String content, LocalDate dueDate);
    void removeTask(int id);
    Task updateTask(int id, String content, LocalDate dueDate, TaskStatus status);
}