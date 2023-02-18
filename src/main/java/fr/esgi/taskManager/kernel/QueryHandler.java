package fr.esgi.taskManager.kernel;

@FunctionalInterface
public interface QueryHandler<Q extends Query, R> {
    R handle(Q query);
}