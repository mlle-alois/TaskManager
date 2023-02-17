package fr.esgi.taskManager.domain;

import java.time.LocalDate;
import java.util.List;

public class TaskServiceImpl implements TaskService {
    @Override
    public List<Task> listTasks() {
        return null;
    }

    @Override
    public Task addTask(String content, LocalDate dueDate) {
        return null;
    }

    @Override
    public void removeTask(int id) {

    }

    @Override
    public Task updateTask(int id, String content, LocalDate dueDate, TaskStatus status) {
        return null;
    }
}
