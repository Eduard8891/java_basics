package main;

import java.util.List;
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

  @GetMapping("/tasks/{id}")
  public ResponseEntity get(@PathVariable int id) {
    Task task = Storage.getTask(id);
    if (task == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return new ResponseEntity(task, HttpStatus.OK);
  }

  @GetMapping("/tasks/")
  public List<Task> list() {
    return Storage.getAllTasks();
  }

  @PostMapping("/tasks/{id}")
  public int add(Task task) {
    return Storage.addTask(task);
  }

  @PutMapping("/tasks/{id}")
  public int updateTask(int id, Task task) {
    return Storage.updateTask(id, task);
  }

  @PutMapping("/tasks/")
  public int updateAllTasks(String setDescription) {
    return Storage.updateAllTasks(setDescription);
  }

  @DeleteMapping("/tasks/{id}")
  public int deleteTask(int id) {
    return Storage.deleteTask(id);
  }

  @DeleteMapping("/tasks/")
  public int deleteAllTasks() {
    return Storage.deleteAllTasks();
  }

}
