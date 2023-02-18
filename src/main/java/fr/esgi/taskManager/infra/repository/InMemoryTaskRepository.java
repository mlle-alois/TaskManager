package fr.esgi.taskManager.infra.repository;

import fr.esgi.taskManager.domain.model.Task;
import fr.esgi.taskManager.domain.model.TaskId;
import fr.esgi.taskManager.domain.repository.TaskRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryTaskRepository implements TaskRepository<TaskId,Task> {
    private final AtomicInteger count = new AtomicInteger(0);
    private Map<TaskId,Task> tasks = new HashMap<>();

    @Override
    public TaskId nextIdentity() {
        return new TaskId(count.incrementAndGet());
    }

    @Override
    public Optional<Task> findById(TaskId id) {
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public List<Task> findAll() {
        return List.copyOf(tasks.values());
    }

    @Override
    public Task save(Task entity) {
        entity.setId(nextIdentity());
        tasks.put(entity.getId(),entity);
        return tasks.get(entity.getId());
    }

    @Override
    public Task update(Task entity) {
        tasks.put(entity.getId(),entity);
        return tasks.get(entity.getId());
    }

    @Override
    public void delete(TaskId id) {
        tasks.remove(id);
    }
}
