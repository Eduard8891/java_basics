package main.controllers;

import lombok.AllArgsConstructor;
import main.model.Task;
import main.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class DefaultController {
    private final TaskService taskService;

    @GetMapping
    public String index(Model model) {
        List<Task> list = taskService.findAll();
        model.addAttribute("taskCount", list.size());
        model.addAttribute("allTasks", list);
        return "index";
    }

    @GetMapping("/{id}")
    public String description(@PathVariable int id, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("number", task.getId());
        model.addAttribute("name", task.getName());
        model.addAttribute("description", task.getDescription());
        return "desc";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable int id) {
        taskService.delete(id);
        return "redirect:/";
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll() {
        taskService.deleteAll();
        return "redirect:/";
    }

    @PostMapping("/add")
    public String addTask(Task task) {
        taskService.add(task);
        return "redirect:/";
    }

    @PatchMapping("/{id}")
    public String changeTask(@ModelAttribute("thisTask") Task task) {
        taskService.update(task);
        return "redirect:/";
    }

    @ModelAttribute("newtask")
    public Task newTask() {
        return new Task();
    }
}
