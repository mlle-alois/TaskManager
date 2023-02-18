package fr.esgi.taskManager.domain.query;

import fr.esgi.taskManager.domain.model.Task;
import fr.esgi.taskManager.domain.repository.TaskRepository;
import fr.esgi.taskManager.kernel.QueryHandler;

import java.util.List;

public class GetAllTasksQueryHandler implements QueryHandler<GetAllTasksQuery,List<Task>> {
    private final TaskRepository repository;

    public GetAllTasksQueryHandler(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> handle(GetAllTasksQuery query) {
        return repository.findAll();
    }
}
