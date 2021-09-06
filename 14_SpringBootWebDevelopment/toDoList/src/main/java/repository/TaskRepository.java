package repository;

import model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface TaskRepository extends CrudRepository<Task, Integer> {
}
