package fr.esgi.taskManager.domain.repository;

import fr.esgi.taskManager.domain.model.Task;
import fr.esgi.taskManager.domain.model.TaskId;
import fr.esgi.taskManager.kernel.Repository;


public interface TaskRepository extends Repository<TaskId, Task> {

}
