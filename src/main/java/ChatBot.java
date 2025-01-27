import java.util.Scanner;

public class ChatBot {
  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

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
      }
      System.out.println("____________________________________________________________\r\n" + //
                userMessage + "\n" + //
                "____________________________________________________________\r\n");
    }
  }
}
