package main.controllers;

import lombok.AllArgsConstructor;
import main.service.TaskService;
import main.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List> list() {
        List<Task> list = taskService.findAll();
        if (list.isEmpty()) {
            throw new NullPointerException("Список дел пустой!");
        }
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getById(@PathVariable int id) {
        if (taskService.findById(id) == null) {
            throw new NullPointerException("Номера дела " + id + " не существует!");
        }
        return new ResponseEntity<>(taskService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Integer> add(Task task) {
        taskService.add(task);
        return new ResponseEntity<>(task.getId(), HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<Integer> update(Task task) {
        taskService.update(task);
        return new ResponseEntity<>(task.getId(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Integer> delete(int id) {
        Task task = taskService.findById(id);
        if (task != null) {
            taskService.delete(task);
            return new ResponseEntity<>(task.getId(), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        taskService.deleteAll();
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }
}
