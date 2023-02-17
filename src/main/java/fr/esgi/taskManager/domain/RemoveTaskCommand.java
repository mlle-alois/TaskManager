package fr.esgi.taskManager.domain;

public class RemoveTaskCommand implements Command {
    private final int id;

    public RemoveTaskCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute() {
        // Implementation
    }
}
