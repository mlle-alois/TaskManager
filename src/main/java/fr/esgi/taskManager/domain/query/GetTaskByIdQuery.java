package fr.esgi.taskManager.domain.query;

import fr.esgi.taskManager.kernel.Query;

import java.util.Objects;

public record GetTaskByIdQuery(Integer taskId) implements Query {
    public GetTaskByIdQuery {
        Objects.requireNonNull(taskId, "taskId must not be null");
    }
}