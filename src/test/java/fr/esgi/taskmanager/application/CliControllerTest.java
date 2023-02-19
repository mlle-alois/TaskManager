package fr.esgi.taskmanager.application;

import fr.esgi.taskmanager.application.exposition.CliController;
import fr.esgi.taskmanager.domain.command.AddTaskCommand;
import fr.esgi.taskmanager.domain.command.RemoveTaskCommand;
import fr.esgi.taskmanager.domain.command.UpdateTaskCommand;
import fr.esgi.taskmanager.domain.model.Task;
import fr.esgi.taskmanager.domain.model.TaskId;
import fr.esgi.taskmanager.domain.model.TaskStatus;
import fr.esgi.taskmanager.domain.query.GetAllTasksQuery;
import fr.esgi.taskmanager.domain.query.GetTaskByIdQuery;
import fr.esgi.taskmanager.kernel.CommandBus;
import fr.esgi.taskmanager.kernel.QueryBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class CliControllerTest {
    private final static String USER_INPUT_ADD = "agenda add -c:task1 -d:2021-06-01\n";
    private final static String USER_INPUT_REMOVE = "agenda remove 1\n";
    private final static String USER_INPUT_UPDATE = "agenda update 1 -c:task1-updated\n";
    private final static String USER_INPUT_LIST = "agenda list\n";
    private final static String USER_INPUT_GET = "agenda get 1\n";
    private final static String USER_INPUT_HELP = "agenda help\n";
    private final static String USER_INPUT_UNKNOWN_COMMAND = "unknown command\n";
    private final static String USER_INPUT_EXIT = "exit\n";

    private static final Task TASK1 = new Task(new TaskId(1), "task1", LocalDate.parse("2021-06-01"), TaskStatus.PENDING, LocalDateTime.now());
    private static final Task TASK2 = new Task(new TaskId(2), "task2", LocalDate.parse("2021-06-02"), TaskStatus.DONE, LocalDateTime.now());
    private static final Task TASK1_UPDATED = new Task(new TaskId(1), "task1-updated", LocalDate.parse("2021-06-01"), TaskStatus.PENDING, LocalDateTime.now());
    private static final List<Task> TASKS = Arrays.asList(TASK1, TASK2);
    private Scanner scanner;
    private CommandBus commandBus;
    private QueryBus queryBus;
    private CliController cliController;
    @Before
    public void setup() {
        scanner = mock(Scanner.class);
        commandBus = mock(CommandBus.class);
        queryBus = mock(QueryBus.class);
        cliController = new CliController(commandBus, queryBus);
    }

    @Test
    public void testCliController_Start_UserInputAdd() {
        when(scanner.nextLine()).thenReturn(USER_INPUT_ADD, USER_INPUT_EXIT);
        cliController.start();

        verify(commandBus).send(new AddTaskCommand("task1", LocalDate.parse("2021-06-01")));
    }

    @Test
    public void testCliController_Start_UserInputRemove() {
        when(scanner.nextLine()).thenReturn(USER_INPUT_REMOVE, USER_INPUT_EXIT);
        cliController.start();

        verify(commandBus).send(new RemoveTaskCommand(1));
    }

    @Test
    public void testCliController_Start_UserInputUpdate() {
        when(scanner.nextLine()).thenReturn(USER_INPUT_UPDATE, USER_INPUT_EXIT);
        cliController.start();

        verify(commandBus).send(new UpdateTaskCommand(1, "task1-updated", null, null));
    }

    @Test
    public void testCliController_Start_UserInputList() {
        when(scanner.nextLine()).thenReturn(USER_INPUT_LIST, USER_INPUT_EXIT);
        when(queryBus.send(new GetAllTasksQuery())).thenReturn(TASKS);
        cliController.start();

        verify(queryBus).send(new GetAllTasksQuery());
    }

    @Test
    public void testCliController_Start_UserInputGet() {
        when(scanner.nextLine()).thenReturn(USER_INPUT_GET, USER_INPUT_EXIT);
        when(queryBus.send(new GetTaskByIdQuery(1))).thenReturn(TASK1);
        cliController.start();

        verify(queryBus).send(new GetTaskByIdQuery(1));
    }


    @Test
    public void handleListCommandTest(){
        when(queryBus.send(new GetAllTasksQuery())).thenReturn(TASKS);
        Assertions.assertDoesNotThrow(()-> cliController.handleAgendaCommand(new String[]{"agenda", "list"}));
    }

    @Test
    public void handleGetCommandTest(){
        Mockito.when(queryBus.send(new GetTaskByIdQuery(1))).thenReturn(TASK1);
        Assertions.assertDoesNotThrow(()-> cliController.handleAgendaCommand(new String[]{"agenda", "get", "1"}));
    }

    @Test
    public void handleUpdateCommandTest(){
        Assertions.assertThrows(DateTimeParseException.class, ()->cliController.handleAgendaCommand(new String[]{"agenda","update","1","-d:2022-23-23"}));
    }

    @Test
    public void handleRemoveCommandTest(){
        Assertions.assertThrows(NumberFormatException.class, ()->cliController.handleAgendaCommand(new String[]{"agenda","remove","a"}));
    }

    @Test
    public void handleAddCommandTest(){
        Assertions.assertDoesNotThrow(()->cliController.handleAgendaCommand(new String[]{"agenda","add","-c:Go to market", "-d:2022-12-12"}));
    }

}
