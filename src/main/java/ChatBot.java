import java.util.Scanner;
import java.util.ArrayList;

public class ChatBot {
  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    ArrayList<String> tasks = new ArrayList<>();

    System.out.println("____________________________________________________________\r\n" + //
            " Hello! I'm Ervin Chatbot!\r\n" + //
            " What can I do for you?\r\n" + //
            "____________________________________________________________\r\n");

    while (true) {
      String userMessage = in.nextLine();
      if (userMessage.equals("bye")) {
        System.out.println("____________________________________________________________\r\n" + //
                "Bye. Hope to see you again soon!" + "\n" + //
                "____________________________________________________________\r\n");
        break;
      } else if (userMessage.equals("list")) {
        System.out.println("____________________________________________________________\r\n");
        for (int i = 0; i < tasks.size(); i++) {
          System.out.println((i+1) + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________\r\n");
      } else {
        tasks.add(userMessage);
        System.out.println("____________________________________________________________\r\n" + //
                "added: " + userMessage + "\n" + //
                "____________________________________________________________\r\n");
      }
    }
    
  }
}
