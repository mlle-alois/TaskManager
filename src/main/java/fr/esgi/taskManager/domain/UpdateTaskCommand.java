package fr.esgi.taskManager.domain;

import java.time.LocalDate;

public class UpdateTaskCommand implements Command {
    private final int id;
    private final String content;
    private final LocalDate dueDate;
    private final TaskStatus status;

    public UpdateTaskCommand(int id, String content, LocalDate dueDate, TaskStatus status) {
        this.id = id;
        this.content = content;
        this.dueDate = dueDate;
        this.status = status;
    }

    @Override
    public void execute() {
        // Implementation
    }
}