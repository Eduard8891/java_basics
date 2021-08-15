package main.controllers;

import main.dao.TaskDao;
import main.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {
    TaskDao taskDao;

    public TaskController(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("allTasks", taskDao.findAll());
        model.addAttribute("tasksCount", taskDao.findAll().size());
        return "index";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Task task, Model model) {
        taskDao.add(task);
        return "index";
    }

}
