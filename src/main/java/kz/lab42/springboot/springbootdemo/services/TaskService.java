package kz.lab42.springboot.springbootdemo.services;


import kz.lab42.springboot.springbootdemo.models.Task;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private long idCounter = 1;

    public List<Task> getAllTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        task.setId(idCounter++);
        tasks.add(task);
    }

    //sorteer per id
    public Task getTaskById(Long id) {
        for (Task t : tasks) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }


    //updating task
    public void updateTask(Task updatedTask) {
        for (Task t : tasks) {
            if (t.getId().equals(updatedTask.getId())) {
                t.setName(updatedTask.getName());
                t.setDescription(updatedTask.getDescription());
                t.setDeadlineDate(updatedTask.getDeadlineDate());
                t.setCompleted(updatedTask.isCompleted());
                break;
            }
        }
    }


    //deleting task
    public void deleteTask(Long id) {
        tasks.removeIf(t -> t.getId().equals(id));
    }


}