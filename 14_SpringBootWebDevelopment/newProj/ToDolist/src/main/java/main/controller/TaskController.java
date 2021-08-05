package main.controller;


import main.dao.TaskDao;
import main.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class TaskController {
    TaskDao taskDao;

    @Autowired
    public TaskController() {
    }

    public TaskController(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @ModelAttribute
    @GetMapping("/index")
    public String index(Model model) {
        if (!taskDao.getAll().isEmpty()) {
            model.addAttribute("allTasks", taskDao.getAll());
            model.addAttribute("taskCount", taskDao.getAll().size());
        }
        return "index";
    }

    @GetMapping("/save")
    public String add(Model model, Task task) {
        model.addAttribute("task", taskDao.add(task));
        return "index";
    }
    @GetMapping("/edit")
    public String edit(Model model, Task task) {
        return "edit";
    }
}
