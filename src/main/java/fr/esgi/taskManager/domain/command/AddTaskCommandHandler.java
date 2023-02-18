package fr.esgi.taskManager.domain.command;

import fr.esgi.taskManager.domain.model.Task;
import fr.esgi.taskManager.domain.repository.TaskRepository;
import fr.esgi.taskManager.kernel.CommandHandler;

public class AddTaskCommandHandler implements CommandHandler<AddTaskCommand, Task> {
    private final TaskRepository repository;

    public AddTaskCommandHandler(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task handle(AddTaskCommand command) {
        var task = new Task(command.content(),command.dueDate());
        return repository.save(task);
    }
}
