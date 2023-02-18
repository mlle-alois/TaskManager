package fr.esgi.taskManager.domain.query;

import fr.esgi.taskManager.domain.exceptions.TaskNotFoundException;
import fr.esgi.taskManager.domain.model.Task;
import fr.esgi.taskManager.domain.model.TaskId;
import fr.esgi.taskManager.domain.repository.TaskRepository;
import fr.esgi.taskManager.kernel.QueryHandler;

public class GetTaskByIdQueryHandler implements QueryHandler<GetTaskByIdQuery, Task> {
    private final TaskRepository repository;

    public GetTaskByIdQueryHandler(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task handle(GetTaskByIdQuery query) {
        return repository.findById(new TaskId(query.taskId()))
                .orElseThrow(() -> new TaskNotFoundException(query.taskId()));
    }
}
