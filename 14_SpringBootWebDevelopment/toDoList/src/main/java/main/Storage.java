package main;

import main.model.Task;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class Storage {

  private volatile static int currentId = 1;
  private static final ConcurrentHashMap<Integer, Task> taskList = new ConcurrentHashMap<Integer, Task>();

  public synchronized static List<Task> getAllTasks() {
    return new Vector<Task>(taskList.values());
  }


  public synchronized static int addTask(Task task) {
    int id = currentId++;
    task.setId(id);
    taskList.put(id, task);
    return id;
  }

  public synchronized static int deleteTask(int id) {
    taskList.remove(id);
    return id;
  }

  public synchronized static int deleteAllTasks() {
    taskList.clear();
    return 0;
  }

  public synchronized static int updateTask(int id, Task task) {
    taskList.put(id, task);
    return id;
  }

  public synchronized static int updateAllTasks(String description) {
    int size = 0;
    for (Map.Entry<Integer, Task> entry : taskList.entrySet()) {
      entry.getValue().setDescription(description);
      size++;
    }
    return size;
  }

  public synchronized static Task getTask(int id) {
    if (taskList.containsKey(id)) {
      return taskList.get(id);
    }
    return null;
  }

}
