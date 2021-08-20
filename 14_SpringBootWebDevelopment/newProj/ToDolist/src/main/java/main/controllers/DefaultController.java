package main.controllers;

import lombok.AllArgsConstructor;
import main.model.Task;
import main.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class DefaultController {
    private final TaskService taskService;

    @GetMapping("/")
    public String index(Model model) {
        List<Task> list = taskService.findAll();
        model.addAttribute("taskCount", list.size());
        model.addAttribute("allTasks", list);
        return "index";
    }

    @GetMapping("allTasks/{id}")
    public String description(@PathVariable int id, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("number", task.getId());
        model.addAttribute("name", task.getName());
        model.addAttribute("description", task.getDescription());
        return "desc";
    }
}
