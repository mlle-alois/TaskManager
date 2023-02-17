package fr.esgi.taskManager.domain.service;

import fr.esgi.taskManager.domain.model.Task;

import java.util.List;

public interface TaskQueryService {
    List<Task> getTasks();
}
