package main.dao;

import lombok.NoArgsConstructor;
import main.model.Task;
import main.model.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TaskDao {
    TaskRepository taskRepository;


    public Task get(int id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    public List getAll() {
        List<Task> list = new ArrayList<>();
        Iterable<Task> task = taskRepository.findAll();
        task.forEach(list::add);
        return list;
    }

    public int add(Task task) {
        taskRepository.save(task);
        return task.getId();
    }

    public int edit(Task task) {
        Optional<Task> optionalTask = taskRepository.findById(task.getId());
        if (optionalTask.isPresent()) {
            taskRepository.delete(optionalTask.get());
            taskRepository.save(task);
            return task.getId();
        }
        return -1;
    }

    public void delete(int id) {
        taskRepository.deleteById(id);
    }

    public void deleteAll() {
        taskRepository.deleteAll();
    }


}
