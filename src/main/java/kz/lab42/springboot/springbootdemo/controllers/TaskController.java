package kz.lab42.springboot.springbootdemo.controllers;

import kz.lab42.springboot.springbootdemo.services.TaskService;
import kz.lab42.springboot.springbootdemo.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // alle tasks
    @GetMapping("/")
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks"; // template name
    }

    // add card
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("task", new Task());
        return "add-task";
    }



    @PostMapping("/add")
    public String addTask(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String deadlineDate) {

        Task newTask = new Task();
        newTask.setName(name);
        newTask.setDescription(description);
        newTask.setDeadlineDate(deadlineDate);
        newTask.setCompleted(false);
        taskService.addTask(newTask);

        return "redirect:/";
    }

    // see details
    @GetMapping("/details/{id}")
    public String viewDetails(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "task-details";
    }

    // Update (after editing)
    @PostMapping("/edit")
    public String editTask(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String deadlineDate,
            @RequestParam(required = false) boolean isCompleted) {

        Task updated = new Task(id, name, description, deadlineDate, isCompleted);
        taskService.updateTask(updated);
        return "redirect:/";
    }

    // Delete
    @PostMapping("/delete")
    public String deleteTask(@RequestParam Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }

}