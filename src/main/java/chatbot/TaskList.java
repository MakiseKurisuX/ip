package chatbot;
import java.util.ArrayList;

import task.Task;
import task.Event;
import task.Task;
import task.Todo;
import task.HeliosException;

public class TaskList {
  private ArrayList<Task> tasks;

  public TaskList() {
    this.tasks = new ArrayList<>();
  }

  public TaskList(ArrayList<Task> tasks) {
    this.tasks = tasks;
  }

  public void addTask(Task task) {
    tasks.add(task);
  }

  public Task removeTask(int index) {
    return tasks.remove(index);
  }

  public Task getTask(int index) {
    return tasks.get(index);
  }

  public int getSize() {
    return tasks.size();
  }

  public ArrayList<Task> getTasks() {
    return tasks;
  }
  
  public void markTask(int index) throws HeliosException {
    if (index < 0 || index >= tasks.size()) {
      throw new HeliosException("You input an invalid number");
    }
    tasks.get(index).setisDone(true);
  }

  public void unmarkTask(int index) throws HeliosException {
    if (index < 0 || index >= tasks.size()) {
      throw new HeliosException("You input an invalid number");
    }
    tasks.get(index).setisDone(false);
  }

  public String listTasks() {
    String returnedString = "";
    if (tasks.size() == 0) {
      return "No tasks available.";
    } else {
      for (int i = 0; i < tasks.size(); i++) {
        returnedString += (i + 1) + ". " + tasks.get(i).getDescription() + "\n";
      }
    }
    return returnedString;
  }
}
