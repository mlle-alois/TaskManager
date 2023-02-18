package fr.esgi.taskManager.kernel;

@FunctionalInterface
public interface CommandHandler<C extends Command, R> {
    R handle(C command);
}
