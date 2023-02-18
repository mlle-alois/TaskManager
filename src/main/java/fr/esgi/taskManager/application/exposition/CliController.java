package fr.esgi.taskManager.application.exposition;

import fr.esgi.taskManager.domain.command.*;
import fr.esgi.taskManager.domain.model.Task;
import fr.esgi.taskManager.domain.model.TaskStatus;
import fr.esgi.taskManager.domain.query.GetAllTasksQuery;
import fr.esgi.taskManager.domain.query.GetAllTasksQueryHandler;
import fr.esgi.taskManager.domain.query.GetTaskByIdQuery;
import fr.esgi.taskManager.domain.query.GetTaskByIdQueryHandler;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CliController {
    private final Scanner scanner;
    private final AddTaskCommandHandler addTaskCommandHandler;
    private final RemoveTaskCommandHandler removeTaskCommandHandler;
    private final UpdateTaskCommandHandler updateTaskCommandHandler;
    private final GetAllTasksQueryHandler getAllTasksQueryHandler;
    private final GetTaskByIdQueryHandler getTaskByIdQueryHandler;

    public CliController( Scanner scanner,
                          AddTaskCommandHandler addTaskCommandHandler,
                          RemoveTaskCommandHandler removeTaskCommandHandler,
                          UpdateTaskCommandHandler updateTaskCommandHandler,
                          GetAllTasksQueryHandler getAllTasksQueryHandler,
                          GetTaskByIdQueryHandler getTaskByIdQueryHandler) {
        this.scanner = scanner;
        this.addTaskCommandHandler = addTaskCommandHandler;
        this.removeTaskCommandHandler = removeTaskCommandHandler;
        this.updateTaskCommandHandler = updateTaskCommandHandler;
        this.getAllTasksQueryHandler = getAllTasksQueryHandler;
        this.getTaskByIdQueryHandler = getTaskByIdQueryHandler;
    }

    public void start() {
        System.out.println("Welcome to the Task Manager CLI!");

        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("Please enter a command:");

            String command = scanner.nextLine().trim();
            String[] commandParts = command.split("\\s+");

            switch (commandParts[0]) {
                case "agenda":
                    handleAgendaCommand(commandParts);
                    break;
                case "exit":
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Unknown command. Please try again.");
            }
        }

        System.out.println("Exiting Task Manager CLI. Goodbye!");
    }

    private void handleAgendaCommand(String[] commandParts) {
        if (commandParts.length == 1) {
            System.out.println("Please provide a subcommand. Use 'agenda help' for more information.");
            return;
        }

        switch (commandParts[1]) {
            case "add":
                handleAddCommand(commandParts);
                break;
            case "remove":
                handleRemoveCommand(commandParts);
                break;
            case "update":
                handleUpdateCommand(commandParts);
                break;
            case "list":
                handleListCommand();
                break;
            case "get":
                handleGetCommand(commandParts);
                break;
            case "help":
                System.out.println("Agenda subcommands:");
                System.out.println("  add -c <content> [-d <due_date>] [-s <status>]");
                System.out.println("  remove <id>");
                System.out.println("  update <id> [-c <content>] [-d <due_date>] [-s <status>]");
                System.out.println("  list");
                System.out.println("  get <id>");
                System.out.println("  help");
                break;
            default:
                System.out.println("Unknown subcommand. Use 'agenda help' for more information.");
        }
    }

    public void handleListCommand() {
        List<Task> tasks = getAllTasksQueryHandler.handle(new GetAllTasksQuery());

        tasks.stream()
                .sorted(Comparator.comparing(Task::getCreationDate).reversed())
                .forEach(task -> {
                    System.out.println(task.toString());
                });
    }

    public void handleGetCommand(String[] commandParts) {
        if (commandParts.length != 2) {
            System.out.println("Invalid command syntax. Usage: agenda remove <id>");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(commandParts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid task ID: " + commandParts[1]);
            return;
        }

        var task = getTaskByIdQueryHandler.handle(new GetTaskByIdQuery(id));
        System.out.println(task.toString());
    }

    private void handleUpdateCommand(String[] commandParts) {
        if (commandParts.length < 4) {
            System.out.println("Invalid command syntax. Usage: agenda update <id> [-c <content>] [-d <due date>] [-s <status>]");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(commandParts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid task ID: " + commandParts[1]);
            return;
        }

        LocalDate dueDate = null;
        TaskStatus status = null;
        String content = null;

        for (int i = 2; i < commandParts.length; i++) {
            String part = commandParts[i];
            if (part.startsWith("-d:")) {
                try {
                    dueDate = LocalDate.parse(part.substring(4));
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid due date: " + part.substring(4));
                    return;
                }
            } else if (part.startsWith("-C:")) {
                try {
                    content = part.substring(4);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid status: " + part.substring(4));
                    return;
                }
            }else if (part.startsWith("-s:")) {
                try {
                    status = TaskStatus.valueOf(part.substring(4).toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid status: " + part.substring(4));
                    return;
                }
            }
        }
        var command = new UpdateTaskCommand(id,content, dueDate, status);
        updateTaskCommandHandler.handle(command);
        System.out.println("Task with ID " + id + " updated successfully");
    }

    private void handleRemoveCommand(String[] commandParts) {
        if (commandParts.length != 2) {
            System.out.println("Invalid command syntax. Usage: agenda remove <id>");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(commandParts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid task ID: " + commandParts[1]);
            return;
        }

        var command = new RemoveTaskCommand(id);
        removeTaskCommandHandler.handle(command);

        System.out.println("Task with ID " + id + " removed successfully");
    }

    private void handleAddCommand(String[] commandParts) {
        String content = null;
        LocalDate dueDate = null;

        for (String part : commandParts) {
            if (part.startsWith("-c:")) {
                content = part.substring(3);
            } else if (part.startsWith("-d:")) {
                String dateString = part.substring(3);
                dueDate = LocalDate.parse(dateString);
            }
        }

        if (content == null) {
            System.out.println("Content is required for adding a task");
            return;
        }

        var command = new AddTaskCommand(content, dueDate);
        addTaskCommandHandler.handle(command);

        System.out.println("Task added successfully");
    }

}



