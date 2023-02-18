package fr.esgi.taskManager.domain.command;

import fr.esgi.taskManager.domain.exceptions.TaskNotFoundException;
import fr.esgi.taskManager.domain.model.TaskId;
import fr.esgi.taskManager.domain.repository.TaskRepository;
import fr.esgi.taskManager.kernel.CommandHandler;

public class RemoveTaskCommandHandler implements CommandHandler<RemoveTaskCommand, Void> {
    private final TaskRepository repository;

    public RemoveTaskCommandHandler(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Void handle(RemoveTaskCommand command) {
        var taskId = new TaskId(command.taskId());
        repository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(command.taskId()));
        repository.delete(taskId);
        return null;
    }
}
