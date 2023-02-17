package fr.esgi.taskManager.domain;

public interface Query<R> {
    R execute();
}
