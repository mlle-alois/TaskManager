package fr.esgi.taskManager.domain.command;

import fr.esgi.taskManager.domain.exceptions.TaskNotFoundException;
import fr.esgi.taskManager.domain.model.Task;
import fr.esgi.taskManager.domain.model.TaskId;
import fr.esgi.taskManager.domain.repository.TaskRepository;
import fr.esgi.taskManager.kernel.CommandHandler;

public class UpdateTaskCommandHandler implements CommandHandler<UpdateTaskCommand, Task> {
    private final TaskRepository repository;

    public UpdateTaskCommandHandler(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task handle(UpdateTaskCommand command) {
        var task = repository.findById(new TaskId(command.taskId())).orElseThrow(() -> new TaskNotFoundException(command.taskId()));
        if(command.content() != null ) task.setContent(command.content());
        if(command.dueDate() != null ) task.setDueDate(command.dueDate());
        if(command.status() != null ) task.setStatus(command.status());
        return repository.update(task);
    }
}
