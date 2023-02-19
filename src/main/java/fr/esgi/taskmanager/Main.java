package fr.esgi.taskmanager;

import fr.esgi.taskmanager.application.exposition.CliController;
import fr.esgi.taskmanager.domain.command.*;

import fr.esgi.taskmanager.domain.query.GetAllTasksQuery;
import fr.esgi.taskmanager.domain.query.GetAllTasksQueryHandler;
import fr.esgi.taskmanager.domain.query.GetTaskByIdQuery;
import fr.esgi.taskmanager.domain.query.GetTaskByIdQueryHandler;
import fr.esgi.taskmanager.infra.repository.InMemoryTaskRepository;
import fr.esgi.taskmanager.kernel.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var taskRepository = new InMemoryTaskRepository();
        var addTaskCommandHandler = new AddTaskCommandHandler(taskRepository);
        var removeTaskCommandHandler = new RemoveTaskCommandHandler(taskRepository);
        var updateTaskCommandHandler = new UpdateTaskCommandHandler(taskRepository);
        var getAllTasksQueryHandler = new GetAllTasksQueryHandler(taskRepository);
        var getTaskByIdQueryHandler = new GetTaskByIdQueryHandler(taskRepository);
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(AddTaskCommand.class, addTaskCommandHandler);
        commandHandlerMap.put(UpdateTaskCommand.class, updateTaskCommandHandler);
        commandHandlerMap.put(RemoveTaskCommand.class, removeTaskCommandHandler);
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap  = new HashMap<>();
        queryHandlerMap.put(GetAllTasksQuery.class, getAllTasksQueryHandler);
        queryHandlerMap.put(GetTaskByIdQuery.class, getTaskByIdQueryHandler);

        var commandBus = new SimpleCommandBus(commandHandlerMap);
        var queryBus = new SimpleQueryBus(queryHandlerMap);
        var controller = new CliController(commandBus,queryBus);
        controller.start();
    }
}

