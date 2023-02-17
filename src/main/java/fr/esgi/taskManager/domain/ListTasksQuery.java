package fr.esgi.taskManager.domain;

import fr.esgi.taskManager.domain.model.Task;

import java.util.List;

public class ListTasksQuery implements Query<List<Task>> {
    @Override
    public List<Task> execute() {
        return null;
    }
}