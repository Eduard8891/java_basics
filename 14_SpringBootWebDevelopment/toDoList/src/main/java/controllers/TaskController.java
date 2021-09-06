package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import model.Task;
import repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @RequestMapping("/")
    public String index(Model model) {
        Iterable <Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (Task task: taskIterable) {
            tasks.add(task);
            model.addAttribute("task");
        }
        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksCount", tasks.size());
        return "index";
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") int id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (!taskOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(taskOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/all/")
    public List<Task> list() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        taskIterable.forEach(tasks::add);
        return tasks;
    }

    @PostMapping("/add/")
    public synchronized int add(@ModelAttribute("task") Task task) {
        Task tempTask = taskRepository.save(task);
        return tempTask.getId();
    }

    @PutMapping("/update/{id}")
    public synchronized int updateTask(int id, Task task) {
        taskRepository.deleteById(id);
        Task tempTask = taskRepository.save(task);
        return tempTask.getId();
    }

    @PutMapping("/updateall/")
    public synchronized int updateAllTasks(String description) {
        Iterable<Task> allTasks = taskRepository.findAll();
        AtomicInteger size = new AtomicInteger();
        allTasks.forEach(element -> {
            element.setDescription(description);
            size.getAndIncrement();
        });
        return size.get();
    }

    @DeleteMapping("/delete/{id}")
    public synchronized int deleteTask(int id) {
        taskRepository.deleteById(id);
        return id;
    }

    @DeleteMapping("/deleteall/")
    public synchronized int deleteAllTasks() {
        taskRepository.deleteAll();
        return 0;
    }

}
