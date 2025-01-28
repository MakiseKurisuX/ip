import java.util.Scanner;
import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class ChatBot {
  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    ArrayList<Task> tasks = new ArrayList<>();

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
        for (int i = 0; i < tasks.size(); i++) {
          System.out.println((i+1) + ". " + tasks.get(i).getDescription());
        }
        System.out.println("____________________________________________________________\r\n");
      } else if (task.startsWith("mark")) {
        int number = Integer.parseInt(task.split(" ")[1]) - 1;
        tasks.get(number).setisDone(true);
        System.out.println("____________________________________________________________\r\n" +
                "Nice! I've marked this task as done:\r\n  " +
                tasks.get(number).getDescription() +
                "\n____________________________________________________________");
      } else if (task.startsWith("unmark")) {
        int number = Integer.parseInt(task.split(" ")[1]) - 1;
        tasks.get(number).setisDone(false);
        System.out.println("____________________________________________________________\r\n" +
                "OK, I've marked this task as not done yet:\r\n  " +
                tasks.get(number).getDescription() +
                "\n____________________________________________________________");
      } else if (task.startsWith("todo")) {
        String description = task.substring(5); // Skip "todo "
        tasks.add(new Todo(description));
        System.out.println("____________________________________________________________\r\n" +
                "Got it. I've added this task:\r\n  " +
                tasks.get(tasks.size() - 1).getDescription() +
                "\nNow you have " + tasks.size() + " tasks in the list.\r\n" +
                "____________________________________________________________");
      } else if (task.startsWith("deadline")) {
        String[] parts = task.substring(9).split(" /by ");
        tasks.add(new Deadline(parts[0], parts[1]));
        System.out.println("____________________________________________________________\r\n" +
                "Got it. I've added this task:\r\n  " +
                tasks.get(tasks.size() - 1).getDescription() +
                "\nNow you have " + tasks.size() + " tasks in the list.\r\n" +
                "____________________________________________________________");
      } else if (task.startsWith("event")) {
        String[] parts = task.substring(6).split(" /from | /to ");
        tasks.add(new Event(parts[0], parts[1], parts[2]));
        System.out.println("____________________________________________________________\r\n" +
                "Got it. I've added this task:\r\n  " +
                tasks.get(tasks.size() - 1).getDescription() +
                "\nNow you have " + tasks.size() + " tasks in the list.\r\n" +
                "____________________________________________________________");
      }
    }

  }
}