import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Storage {
  private String filePath;
  
  public Storage(String filePath) {
    this.filePath = filePath;
  }

  public TaskList loadTasks() {
    TaskList tasks = new TaskList();
    try (Scanner fileScanner = new Scanner(new File(filePath))) {
      while (fileScanner.hasNextLine()) {
        String currentLine = fileScanner.nextLine();
        String[] splittedLine = currentLine.split(" \\| ");
        if (splittedLine[0].equals("T")) {
          tasks.addTask(new Todo(splittedLine[2]));
        } else if (splittedLine[0].equals("D")) {
          tasks.addTask(new Deadline(splittedLine[2], splittedLine[3]));
        } else if (splittedLine[0].equals("E")) {
          String[] fromToSplitted = splittedLine[3].split(" - ");
          tasks.addTask(new Event(splittedLine[2], fromToSplitted[0], fromToSplitted[1]));
        }
        if (splittedLine[1].equals("1")) {
          tasks.getTask(tasks.getSize() - 1).setisDone(true);
        }
      }
    } catch (IOException e) {
      System.out.println("File does not exist. Creating File.");
    }
    return tasks;
  }

  public void saveTasks(TaskList tasks) {
    try (PrintWriter writer = new PrintWriter("./data/helios.txt")) {
      ArrayList<Task> tasksArr = tasks.getTasks();
      for (Task task : tasksArr) {
        String taskType = task instanceof Todo ? "T" : task instanceof Deadline ? "D" : "E";
        String isDone = task.getisDone() ? "1" : "0";
        String taskDetails = "";

        if (taskType.equals("T")) {
          taskDetails = task.getPureDescription();
        } else if (taskType.equals("D")) {
          Deadline deadline = (Deadline) task;
          taskDetails = deadline.getPureDescription() + " | " + deadline.getBy();
        } else {
          Event event = (Event) task;
          taskDetails = event.getPureDescription() + " | " + event.getFrom() + " - " + event.getTo();
        }

        writer.println(taskType + " | " + isDone + " | " + taskDetails);
      }
    } catch (IOException e) {
        System.out.println("Error writing to ./data/helios.txt: " + e.getMessage());
    }
  }

}
