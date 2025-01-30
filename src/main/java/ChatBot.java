import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import task.HeliosException;

public class ChatBot {

  public static void saveTasks (ArrayList<Task> tasks) {
    try (PrintWriter writer = new PrintWriter("./data/helios.txt")) {
      for (Task task : tasks) {
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

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    ArrayList<Task> tasks = new ArrayList<>();

    /*
     * Scan files
     */
    try (Scanner fileScanner = new Scanner(new File("./data/helios.txt"))) {
      while (fileScanner.hasNextLine()) {
        String currentLine = fileScanner.nextLine();
        String[] splittedLine = currentLine.split(" \\| ");
        if (splittedLine[0].equals("T")) {
          tasks.add(new Todo(splittedLine[2]));
        } else if (splittedLine[0].equals("D")) {
          tasks.add(new Deadline(splittedLine[2], splittedLine[3]));
        } else if (splittedLine[0].equals("E")) {
          String[] fromToSplitted = splittedLine[3].split(" - ");
          tasks.add(new Event(splittedLine[2], fromToSplitted[0], fromToSplitted[1]));
        }
        if (splittedLine[1].equals("1")) {
          tasks.get(tasks.size()-1).setisDone(true);
        }
      }
    } catch (IOException e) {
      System.out.println("File does not exist. Creating File.");
    }

    System.out.println("____________________________________________________________\r\n" + //
            " Hello! I'm Ervin Chatbot!\r\n" + //
            " What can I do for you?\r\n" + //
            "____________________________________________________________\r\n");

    while (true) {
      String task = in.nextLine();
      if (task.equals("bye")) {
        System.out.println("____________________________________________________________\r\n" + //
                "Bye. Hope to see you again soon!" + "\n" + //
                "____________________________________________________________\r\n");
        break;
      } else if (task.equals("list")) {
        System.out.println("____________________________________________________________\r\n");
        if (tasks.isEmpty()) {
          System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
              System.out.println((i + 1) + ". " + tasks.get(i).getDescription());
            }
        }
        System.out.println("____________________________________________________________\r\n");
      } else if (task.startsWith("mark")) {
        try {
          int number = Integer.parseInt(task.split(" ")[1]) - 1;
          if (number < 0 || number >= tasks.size()) {
            throw new HeliosException("You input an invalid number");
          }
          tasks.get(number).setisDone(true);
          System.out.println("____________________________________________________________\r\n" +
                  "Nice! I've marked this task as done:\r\n  " +
                  tasks.get(number).getDescription() +
                  "\n____________________________________________________________");
        } catch (Exception e) {
          System.out.println("____________________________________________________________\r\n" +
                  " OOPS!!! " + e.getMessage() + "\r\n" +
                  "____________________________________________________________");
        }
      } else if (task.startsWith("unmark")) {
        try {
          int number = Integer.parseInt(task.split(" ")[1]) - 1;
          if (number < 0 || number >= tasks.size()) {
            throw new HeliosException("You input an invalid number");
          }
          tasks.get(number).setisDone(false);
          System.out.println("____________________________________________________________\r\n" +
                  "OK, I've marked this task as not done yet:\r\n  " +
                  tasks.get(number).getDescription() +
                  "\n____________________________________________________________");
        } catch (Exception e) {
          System.out.println("____________________________________________________________\r\n" +
                  " OOPS!!! " + e.getMessage() + "\r\n" +
                  "____________________________________________________________");
        }
      } else if (task.startsWith("todo")) {
        try {
          if (task.length() <= 4) {
            throw new HeliosException("The description of a todo cannot be empty.");
          }
          String description = task.substring(5);
          tasks.add(new Todo(description));
          System.out.println("____________________________________________________________\r\n" +
                  "Got it. I've added this task:\r\n  " +
                  tasks.get(tasks.size() - 1).getDescription() +
                  "\nNow you have " + tasks.size() + " tasks in the list.\r\n" +
                  "____________________________________________________________");
        } catch (Exception e) {
          System.out.println("____________________________________________________________\r\n" +
                  " OOPS!!! " + e.getMessage() + "\r\n" +
                  "____________________________________________________________");
        }
      } else if (task.startsWith("deadline")) {
        try {
          String[] parts = task.substring(9).split(" /by ");
          if (parts.length == 1) {
            throw new HeliosException("The description of a deadline cannot be empty.");
          } else if (parts.length != 2) {
            throw new HeliosException("Deadline must have a /by time.");
          }
          tasks.add(new Deadline(parts[0], parts[1]));
          System.out.println("____________________________________________________________\r\n" +
                  "Got it. I've added this task:\r\n  " +
                  tasks.get(tasks.size() - 1).getDescription() +
                  "\nNow you have " + tasks.size() + " tasks in the list.\r\n" +
                  "____________________________________________________________");
        } catch (Exception e) {
          System.out.println("____________________________________________________________\r\n" +
                  " OOPS!!! " + e.getMessage() + "\r\n" +
                  "____________________________________________________________");
        }
      } else if (task.startsWith("event")) {
        try {
          String[] parts = task.substring(6).split(" /from | /to ");
          if (parts.length == 1) {
            throw new HeliosException("The description of an event cannot be empty.");
          } else if (parts.length != 3) {
            throw new HeliosException("Event must have a /from and /to time.");
          } 
          tasks.add(new Event(parts[0], parts[1], parts[2]));
          System.out.println("____________________________________________________________\r\n" +
                  "Got it. I've added this task:\r\n  " +
                  tasks.get(tasks.size() - 1).getDescription() +
                  "\nNow you have " + tasks.size() + " tasks in the list.\r\n" +
                  "____________________________________________________________");
        } catch (Exception e) {
          System.out.println("____________________________________________________________\r\n" +
                  " OOPS!!! " + e.getMessage() + "\r\n" +
                  "____________________________________________________________");
        }
      } else if (task.startsWith("delete")) {
        try {
          int number = Integer.parseInt(task.split(" ")[1]) - 1;
          if (number < 0 || number >= tasks.size()) {
            throw new HeliosException("You input an invalid number");
          }
          System.out.println("____________________________________________________________\r\n" +
                  "Noted. I've removed this task:\r\n  " +
                  tasks.get(number).getDescription() +
                  "\nNow you have " + tasks.size() + " tasks in the list.\r\n" +
                  "\n____________________________________________________________");
          tasks.remove(number);
        } catch (Exception e) {
          System.out.println("____________________________________________________________\r\n" +
                  " OOPS!!! " + e.getMessage() + "\r\n" +
                  "____________________________________________________________");
        }
      } else {
        System.out.println("____________________________________________________________\r\n" +
                "OOPS!!! I'm sorry, but I don't know what that means :-(\r\n  " +
                "____________________________________________________________");
      }

      /*
       * Write into File
       */
      saveTasks(tasks);

    }

  }
}