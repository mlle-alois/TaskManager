package fr.esgi.taskmanager.application.exposition;

import fr.esgi.taskmanager.domain.command.*;
import fr.esgi.taskmanager.domain.model.Task;
import fr.esgi.taskmanager.domain.model.TaskStatus;
import fr.esgi.taskmanager.domain.query.GetAllTasksQuery;
import fr.esgi.taskmanager.domain.query.GetTaskByIdQuery;
import fr.esgi.taskmanager.kernel.CommandBus;
import fr.esgi.taskmanager.kernel.QueryBus;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class CliController {
    private Scanner scanner;
    private final CommandBus commandBus;
    private final QueryBus queryBus;
    private static final Logger logger = Logger.getLogger(CliController.class.getName());

    public CliController( CommandBus commandHandlers, QueryBus queryHandlers) {
        this.scanner = new Scanner(System.in);
        this.commandBus = commandHandlers;
        this.queryBus = queryHandlers;
    }

    public void start() {
        logger.info("Welcome to the Task Manager CLI!");

        boolean keepRunning = true;
        while (keepRunning) {
            logger.info("Please enter a command:");

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
                    logger.info("Unknown command. Please try again.");
            }
        }

        logger.info("Exiting Task Manager CLI. Goodbye!");
    }

    public void handleAgendaCommand(String[] commandParts) {
        if (commandParts.length == 1) {
            logger.info("Please provide a subcommand. Use 'agenda help' for more information.");
            return;
        }

        switch (commandParts[1].trim().toLowerCase()) {
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
                logger.info("Agenda subcommands:");
                logger.info("  add -c:<content> -d:<due_date>");
                logger.info("  remove <id>");
                logger.info("  update <id> [-c:<content>] [-d:<due_date>] [-s:<status>]");
                logger.info("  list");
                logger.info("  get <id>");
                logger.info("  help");
                break;
            default:
                logger.info("Unknown subcommand. Use 'agenda help' for more information.");
        }
    }

    private void handleListCommand() {
        List<Task> tasks = queryBus.send(new GetAllTasksQuery());

        tasks.stream()
                .sorted(Comparator.comparing(Task::getCreationDate).reversed())
                .forEach(task -> {
                    logger.info(task.toString());
                });
    }

    private void handleGetCommand(String[] commandParts) {
        if (commandParts.length != 2) {
            logger.info("Invalid command syntax. Usage: agenda remove <id>");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(commandParts[1]);
        } catch (NumberFormatException e) {
            logger.info("Invalid task ID: " + commandParts[1]);
            return;
        }

        var task = queryBus.send(new GetTaskByIdQuery(id));
        logger.info(task.toString());
    }

    private void handleUpdateCommand(String[] commandParts) {
        if (commandParts.length < 4) {
            logger.info("Invalid command syntax. Usage: agenda update <id> [-c <content>] [-d <due date>] [-s <status>]");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(commandParts[1]);
        } catch (NumberFormatException e) {
            logger.info("Invalid task ID: " + commandParts[1]);
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
                    logger.info("Invalid due date: " + part.substring(4));
                    return;
                }
            } else if (part.startsWith("-C:")) {
                try {
                    content = part.substring(4);
                } catch (IllegalArgumentException e) {
                    logger.info("Invalid status: " + part.substring(4));
                    return;
                }
            }else if (part.startsWith("-s:")) {
                try {
                    status = TaskStatus.valueOf(part.substring(4).toUpperCase());
                } catch (IllegalArgumentException e) {
                    logger.info("Invalid status: " + part.substring(4));
                    return;
                }
            }
        }
        var command = new UpdateTaskCommand(id,content, dueDate, status);
        commandBus.send(command);
        logger.info("Task with ID " + id + " updated successfully");
    }

    private void handleRemoveCommand(String[] commandParts) {
        if (commandParts.length != 2) {
            logger.info("Invalid command syntax. Usage: agenda remove <id>");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(commandParts[1]);
        } catch (NumberFormatException e) {
            logger.info("Invalid task ID: " + commandParts[1]);
            return;
        }

        var command = new RemoveTaskCommand(id);
        commandBus.send(command);

        logger.info("Task with ID " + id + " removed successfully");
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
            logger.info("Content is required for adding a task");
            return;
        }

        var command = new AddTaskCommand(content, dueDate);
        commandBus.send(command);

        logger.info("Task added successfully");
    }

}



