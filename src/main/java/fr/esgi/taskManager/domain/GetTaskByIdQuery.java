package fr.esgi.taskManager.domain;

import fr.esgi.taskManager.domain.model.Task;

public class GetTaskByIdQuery implements Query<Task> {
    private final int id;

    public GetTaskByIdQuery(int id) {
        this.id = id;
    }

    @Override
    public Task execute() {
        return null;
    }
}