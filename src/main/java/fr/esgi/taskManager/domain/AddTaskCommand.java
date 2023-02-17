package fr.esgi.taskManager.domain;

import java.time.LocalDate;

public class AddTaskCommand implements Command {
private final String content;
private final LocalDate dueDate;

public AddTaskCommand(String content, LocalDate dueDate) {
        this.content = content;
        this.dueDate = dueDate;
        }

@Override
public void execute() {
        // Implementation
        }
        }
