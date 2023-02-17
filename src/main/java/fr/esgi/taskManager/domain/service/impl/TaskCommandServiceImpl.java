package fr.esgi.taskManager.domain.service.impl;

import fr.esgi.taskManager.domain.model.Task;
import fr.esgi.taskManager.domain.model.TaskStatus;
import fr.esgi.taskManager.domain.repository.TaskRepository;
import fr.esgi.taskManager.domain.service.TaskCommandService;

import java.time.LocalDate;
import java.util.List;

public class TaskCommandServiceImpl implements TaskCommandService {
    private final TaskRepository repository;

    public TaskCommandServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task addTask(String content, LocalDate dueDate) {
        return null;
    }

    @Override
    public void removeTask(int id) {

    }

    @Override
    public Task updateTask(int id, String content, LocalDate dueDate, TaskStatus status) {
        return null;
    }
}
