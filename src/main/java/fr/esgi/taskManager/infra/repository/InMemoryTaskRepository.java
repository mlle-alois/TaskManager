package fr.esgi.taskManager.infra.repository;

import fr.esgi.taskManager.domain.model.Task;
import fr.esgi.taskManager.domain.repository.TaskRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryTaskRepository implements TaskRepository {
    private Map<Integer, Task> tasks = new HashMap<>();

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public Optional<Task> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void save(Task task) {

    }

    @Override
    public void delete(Task task) {

    }
}
