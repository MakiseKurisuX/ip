package chatbot;
import java.util.ArrayList;

import task.Task;
import task.Event;
import task.Task;
import task.Todo;
import task.HeliosException;

/*
 * Manages an ArrayList of tasks, allows you to add, remove, mark and list tasks.
 */
public class TaskList {
  private ArrayList<Task> tasks;

  /*
   * Constructor to create an empty TaskList.
   */
  public TaskList() {
    this.tasks = new ArrayList<>();
  }

  /*
   * Constructor to create a TaskList with an ArrayList of tasks.
   * 
   * @param tasks The list of tasks to be initialized.
   */
  public TaskList(ArrayList<Task> tasks) {
    this.tasks = tasks;
  }

  /*
   * Adds a task to task list.
   * 
   * @param task The task to be added.
   */
  public void addTask(Task task) {
    tasks.add(task);
  }

  /*
   * Removes a task from the list by its index.
   * 
   * @param index The zero-based index of the task to be removed.
   * @return The removed task.
   */
  public Task removeTask(int index) {
    return tasks.remove(index);
  }

  /*
   * Returns a task by its index.
   * 
   * @param index The zero-based index of the task to be returned.
   * @return The task to be returned.
   */
  public Task getTask(int index) {
    return tasks.get(index);
  }

  /*
   * Get number of tasks in list.
   * 
   * @return The number of tasks remaining.
   */
  public int getSize() {
    return tasks.size();
  }

  /*
   * Gets all the tasks in the form of an ArrayList.
   * 
   * @return The returned tasks.
   */
  public ArrayList<Task> getTasks() {
    return tasks;
  }
  
  /*
   * Marks a task as done by its index.
   *
   * @param index The zero-based index of the task to be marked as done.
   * @throws HeliosException If the index is out of bounds.
   */
  public void markTask(int index) throws HeliosException {
    if (index < 0 || index >= tasks.size()) {
      throw new HeliosException("You input an invalid number");
    }
    tasks.get(index).setisDone(true);
  }

  /*
   * Marks a task as not done by its index.
   *
   * @param index The zero-based index of the task to be marked as not done.
   * @throws HeliosException If the index is out of bounds.
   */
  public void unmarkTask(int index) throws HeliosException {
    if (index < 0 || index >= tasks.size()) {
      throw new HeliosException("You input an invalid number");
    }
    tasks.get(index).setisDone(false);
  }

  /*
   * Returns a formatted list of all tasks.
   * If there are no tasks, returns "No tasks available."
   *
   * @return A formatted string containing all tasks.
   */
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
