package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/task/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (!taskOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(taskOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/tasks/")
    public List<Task> list() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        taskIterable.forEach(tasks::add);
        return tasks;
    }

    @PostMapping("/addtask/")
    public int add(Task task) {
        Task tempTask = taskRepository.save(task);
        return tempTask.getId();
    }

    @PutMapping("/updatetask/{id}")
    public int updateTask(int id, Task task) {
        taskRepository.deleteById(id);
        Task tempTask = taskRepository.save(task);
        return tempTask.getId();
    }

    @PutMapping("/updatetasks/")
    public int updateAllTasks(String description) {
        Iterable<Task> allTasks = taskRepository.findAll();
        AtomicInteger size = new AtomicInteger();
        allTasks.forEach(element -> {
            element.setDescription(description);
            size.getAndIncrement();
        });
        return size.get();
    }

    @DeleteMapping("/deletetask/{id}")
    public int deleteTask(int id) {
        taskRepository.deleteById(id);
        return id;
    }

    @DeleteMapping("/deletetasks/")
    public int deleteAllTasks() {
        taskRepository.deleteAll();
        return 0;
    }

}
