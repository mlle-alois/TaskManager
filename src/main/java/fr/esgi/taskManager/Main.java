package fr.esgi.taskManager;

public class Main {
    private static String filename = System.getProperty("user.home") + "/.consoleagenda/log.txt";

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Error: No command specified.");
            System.exit(1);
        }

        try {
            switch (args[0]) {
                case "list":
                    listTasks();
                    break;
                case "add":
                    addTask(args);
                    break;
                case "remove":
                    removeTask(args);
                    break;
                case "update":
                    updateTask(args);
                    break;
                default:
                    System.out.println("Error: Invalid command.");
                    System.exit(1);
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to write to log file.");
            System.exit(1);
        }
    }
}
