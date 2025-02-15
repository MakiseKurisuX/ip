package chatbot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDateTime;

import task.Task;
import task.Event;
import task.Deadline;
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
        tasks.get(index).setIsDone(true);
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
        tasks.get(index).setIsDone(false);
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

    /*
     * Returns a formatted list of all tasks matching the keyword.
     * If there are no tasks, returns "There are no matching tasks!"
     *
     * @return A formatted string containing all tasks that match the keyword.
     */
    public String findTasks(String keyword) {
        String returnedString = "Here are all the matching tasks in your list";
        for (Task task : tasks) {
            String[] descriptionWords = task.getPureDescription().split(" ");
            for (String word : descriptionWords) {
                if (word.trim().equals(keyword)) {
                    returnedString += "\n" + task.getDescription();
                    break;
                }
            }
        }
        if (returnedString.equals("Here are all the matching tasks in your list")) {
            return "There are no matching tasks!";
        }
        return returnedString;
    }

    /*
     * Sorts the tasks chronologically by the following order:
     * - First sorted by their first sorting key [by for Deadline, From for Event]
     * - Then sorted by their second sorting key [to for Event]
     * - Tasks do not have a deadline and are thus sorted as lowest priority.
     */
    public void sortTasks() {
        Collections.sort(tasks, (t1, t2) -> {
            LocalDateTime key1 = t1.getSortKey();
            LocalDateTime key2 = t2.getSortKey();
            if (key1 == null && key2 == null) {
                return 0; 
            } else if (key1 == null) {
                return 1; 
            } else if (key2 == null) {
                return -1; 
            }
            if (t1 instanceof Event && t2 instanceof Event) {
                if (t1.getSortKey().equals(t2.getSortKey())) {
                    return t1.getSortKey2().compareTo(t2.getSortKey2());
                } else {
                    return t1.getSortKey().compareTo(t2.getSortKey());
                }
            } else {
                return t1.getSortKey().compareTo(t2.getSortKey());
            }
        });
    }
}
