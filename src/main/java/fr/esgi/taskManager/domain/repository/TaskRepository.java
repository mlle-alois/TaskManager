package fr.esgi.taskManager.domain.repository;

import fr.esgi.taskManager.domain.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();
    Optional<Task> findById(int id);
    void save(Task task);
    void delete(Task task);
}
