package fr.esgi.taskManager.domain.exceptions;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(int id) {
        super("Task with ID " + id + " not found.");
    }
}