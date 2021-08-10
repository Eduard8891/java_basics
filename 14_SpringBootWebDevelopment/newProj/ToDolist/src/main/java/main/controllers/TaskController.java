package main.controllers;

import lombok.NoArgsConstructor;
import main.model.Task;
import main.dao.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {
    TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @RequestMapping("/")
    public String index(Model model) {
        ArrayList<Task> list = new ArrayList<>();
        Iterable<Task> task = taskRepository.findAll();
        task.forEach(list::add);
        model.addAttribute("allTasks", list);
        model.addAttribute("tasksCount", list.size());
        return "index";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Task task, Model model) {
        taskRepository.save(task);
        return "/index";
    }

}
