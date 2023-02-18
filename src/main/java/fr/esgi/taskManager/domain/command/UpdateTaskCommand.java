package fr.esgi.taskManager.domain.command;

import fr.esgi.taskManager.domain.model.TaskStatus;
import fr.esgi.taskManager.kernel.Command;

import java.time.LocalDate;
import java.util.Objects;

public record UpdateTaskCommand(Integer taskId, String content, LocalDate dueDate, TaskStatus status) implements Command {
    public UpdateTaskCommand {
        Objects.requireNonNull(taskId, "taskId must not be null");
    }
}