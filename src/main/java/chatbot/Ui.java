package chatbot;
import java.util.Scanner;

public class Ui {
  private Scanner scanner;

  public Ui() {
    this.scanner = new Scanner(System.in);
  }

  public String readLine() {
    return scanner.nextLine();
  }

  public void welcomeMessage() {
    System.out.println("____________________________________________________________\r\n" + //
            " Hello! I'm Ervin Chatbot!\r\n" + //
            " What can I do for you?\r\n" + //
            "____________________________________________________________\r\n");
  }

  public void goodbyeMessage() {
    System.out.println("____________________________________________________________\r\n" + //
                "Bye. Hope to see you again soon!" + "\n" + //
                "____________________________________________________________\r\n");
  }

  public void showMessage(String message) {
    System.out.println("____________________________________________________________\r\n" + //
                message + "\n" + //
                "____________________________________________________________\r\n");
  }

  public void showError(String error) {
    System.out.println("____________________________________________________________\r\n" + //
                "OOPS!!! " + error + "\n" + //
                "____________________________________________________________\r\n");
  }
}
