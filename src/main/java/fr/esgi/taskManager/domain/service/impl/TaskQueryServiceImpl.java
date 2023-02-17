package fr.esgi.taskManager.domain.service.impl;

import fr.esgi.taskManager.domain.model.Task;
import fr.esgi.taskManager.domain.repository.TaskRepository;
import fr.esgi.taskManager.domain.service.TaskQueryService;

import java.util.List;

public class TaskQueryServiceImpl implements TaskQueryService {
    private final TaskRepository repository;

    public TaskQueryServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> getTasks() {
        return null;
    }
}
