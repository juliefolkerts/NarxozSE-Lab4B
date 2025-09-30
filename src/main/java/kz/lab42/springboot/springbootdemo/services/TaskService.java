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

    public Task getTaskById(Long id) {
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void updateTask(Task updatedTask) {
        Task existing = getTaskById(updatedTask.getId());
        if (existing != null) {
            existing.setName(updatedTask.getName());
            existing.setDeadlineDate(updatedTask.getDeadlineDate());
            existing.setCompleted(updatedTask.isCompleted());
        }
    }

    public void deleteTask(Long id) {
        tasks.removeIf(t -> t.getId().equals(id));
    }
}