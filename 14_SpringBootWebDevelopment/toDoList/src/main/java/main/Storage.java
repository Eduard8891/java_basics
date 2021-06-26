package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Storage {

  private static int currentId = 1;
  private static HashMap<Integer, Task> taskList = new HashMap<>();

  public static List<Task> getAllTasks() {
    ArrayList<Task> tasks = new ArrayList<>();
    tasks.addAll(taskList.values());
    return tasks;
  }


  public static int addTask(Task task) {
    int id = currentId++;
    task.setId(id);
    taskList.put(id, task);
    return id;
  }

  public static int deleteTask(int id) {
    taskList.remove(id);
    return id;
  }

  public static int deleteAllTasks() {
    taskList.clear();
    return 0;
  }

  public static int updateTask(int id, Task task) {
    taskList.put(id, task);
    return id;
  }

  public static int updateAllTasks(String description) {
    int size = 0;
    for (Map.Entry<Integer, Task> entry : taskList.entrySet()) {
      entry.getValue().setDescription(description);
      size++;
    }
    return size;
  }

  public static Task getTask(int id) {
    if (taskList.containsKey(id)) {
      return taskList.get(id);
    }
    return null;
  }

}
