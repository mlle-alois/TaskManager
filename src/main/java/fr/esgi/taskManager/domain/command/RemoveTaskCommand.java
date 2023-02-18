package fr.esgi.taskManager.domain.command;

import fr.esgi.taskManager.kernel.Command;

import java.util.Objects;

public record RemoveTaskCommand(Integer taskId) implements Command {
    public RemoveTaskCommand {
        Objects.requireNonNull(taskId, "taskId must not be null");
    }
}
