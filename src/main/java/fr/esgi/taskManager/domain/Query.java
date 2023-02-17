package fr.esgi.taskManager.domain;

public interface Query<T> {
    T execute();
}
