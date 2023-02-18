package fr.esgi.taskManager.kernel;

public interface QueryBus {
    <Q extends Query, R> R send(Q query);
}
