package main.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import main.model.Task;
import main.repo.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    public int add(Task task) {
        taskRepository.save(task);
        return task.getId();
    }

    public int delete(Task task) {
        Optional<Task> optionalTask = taskRepository.findById(task.getId());
        if (optionalTask.isPresent()) {
            taskRepository.deleteById(task.getId());
            return task.getId();
        }
        return -1;
    }

    public int update(Task task) {
        if (taskRepository.existsById(task.getId())) {
            taskRepository.deleteById(task.getId());
            taskRepository.save(task);
            return task.getId();
        }
        return -1;
    }

    public List<Task> findAll() {
        List<Task> list = new ArrayList<>();
        Iterable<Task> task = taskRepository.findAll();
        task.forEach(list::add);
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }
    public Task findById(int id) {
        Optional<Task> temp = taskRepository.findById(id);
        return temp.orElse(null);
    }

    public void deleteAll() {
        taskRepository.deleteAll();
    }
}
