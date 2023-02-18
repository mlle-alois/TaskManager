package fr.esgi.taskManager;

import fr.esgi.taskManager.application.exposition.CliController;
import fr.esgi.taskManager.domain.command.AddTaskCommandHandler;
import fr.esgi.taskManager.domain.command.RemoveTaskCommandHandler;
import fr.esgi.taskManager.domain.command.UpdateTaskCommandHandler;
import fr.esgi.taskManager.domain.query.GetAllTasksQueryHandler;
import fr.esgi.taskManager.domain.query.GetTaskByIdQueryHandler;
import fr.esgi.taskManager.infra.repository.InMemoryTaskRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var taskRepository = new InMemoryTaskRepository();
        var addTaskCommandHandler = new AddTaskCommandHandler(taskRepository);
        var removeTaskCommandHandler = new RemoveTaskCommandHandler(taskRepository);
        var updateTaskCommandHandler = new UpdateTaskCommandHandler(taskRepository);
        var getAllTasksQueryHandler = new GetAllTasksQueryHandler(taskRepository);
        var getTaskByIdQueryHandler = new GetTaskByIdQueryHandler(taskRepository);
        var controller = new CliController(scanner, addTaskCommandHandler, removeTaskCommandHandler,updateTaskCommandHandler,getAllTasksQueryHandler,getTaskByIdQueryHandler);
        controller.start();
    }
}

