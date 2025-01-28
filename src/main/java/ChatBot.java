import java.util.Scanner;
import java.util.ArrayList;
import task.Task;

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
          System.out.println((i+1) + ". " + "[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).getDescription());
        }
        System.out.println("____________________________________________________________\r\n");
      } else if (task.split(" ")[0].equals("mark")) {
        int number = Integer.parseInt(task.split(" ")[1]) - 1;
        tasks.get(number).setisDone(true);
        System.out.println("____________________________________________________________\r\n" + //
                "Nice! I've marked this task as done:" + "\n" + //
                 "[" + tasks.get(number).getStatusIcon() + "] " + tasks.get(number).getDescription() + "\n" + //
                "____________________________________________________________\r\n");
      } else if (task.split(" ")[0].equals("unmark")) {
        int number = Integer.parseInt(task.split(" ")[1]) - 1;
        tasks.get(number).setisDone(false);
        System.out.println("____________________________________________________________\r\n" + //
                "OK, I've marked this task as not done yet:" + "\n" + //
                "[" + tasks.get(number).getStatusIcon() + "] " + tasks.get(number).getDescription() + "\n" + //
                "____________________________________________________________\r\n");
      } else {
        tasks.add(new Task(task));
        System.out.println("____________________________________________________________\r\n" + //
                "added: " + task + "\n" + //
                "____________________________________________________________\r\n");
      }
    }

  }
}
