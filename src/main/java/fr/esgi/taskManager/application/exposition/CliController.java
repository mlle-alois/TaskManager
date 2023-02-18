package fr.esgi.taskManager.application.exposition;

import fr.esgi.taskManager.domain.command.AddTaskCommandHandler;
import fr.esgi.taskManager.domain.command.RemoveTaskCommandHandler;
import fr.esgi.taskManager.domain.command.UpdateTaskCommandHandler;
import fr.esgi.taskManager.domain.query.GetAllTasksQueryHandler;
import fr.esgi.taskManager.domain.query.GetTaskByIdQueryHandler;

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
            case "help":
                System.out.println("Agenda subcommands:");
                System.out.println("  add -c <content> [-d <due_date>] [-s <status>]");
                System.out.println("  remove <id>");
                System.out.println("  update <id> [-c <content>] [-d <due_date>] [-s <status>]");
                System.out.println("  list");
                System.out.println("  help");
                break;
            default:
                System.out.println("Unknown subcommand. Use 'agenda help' for more information.");
        }
    }

    private void handleListCommand() {
    }

    private void handleUpdateCommand(String[] commandParts) {
    }

    private void handleRemoveCommand(String[] commandParts) {
    }

    private void handleAddCommand(String[] commandParts) {
    }

}



