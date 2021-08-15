package main.controllers;

import main.dao.TaskDao;
import main.model.Task;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
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

    @GetMapping("/get/{id}")
    public String getById(@PathVariable int id) {
        return taskDao.findById(id).getName();
    }

    @PostMapping("/add")
    public int add(Task task) {
        taskDao.add(task);
        return task.getId();
    }

    @PatchMapping("/update")
    public int update(Task task) {
        taskDao.update(task);
        return task.getId();
    }

    @DeleteMapping("/delete")
    public int delete(int id) {
        Task task = taskDao.findById(id);
        if (task != null) {
            taskDao.delete(task);
            return id;
        }
        return -1;
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll() {
        taskDao.deleteAll();
        return "done";
    }
}
