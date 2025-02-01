package chatbot;
import java.util.Scanner;

/*
 * The Ui class handles the interactions with the user.
 * It provides methods to read input and display formatted messages.
 */
public class Ui {
  private Scanner scanner;

  /*
   * Constructor to create Ui object and initializes Scanner.
   */
  public Ui() {
    this.scanner = new Scanner(System.in);
  }

  /*
   * Reads a single line of input from the user.
   * 
   * @return The inputted string.
   */
  public String readLine() {
    return scanner.nextLine();
  }

  /*
   * Displays welcome message.
   */
  public void welcomeMessage() {
    System.out.println("____________________________________________________________\r\n" + //
            " Hello! I'm Ervin Chatbot!\r\n" + //
            " What can I do for you?\r\n" + //
            "____________________________________________________________\r\n");
  }

  /*
   * Displays goodbye message for when user inputs 'bye'.
   */
  public void goodbyeMessage() {
    System.out.println("____________________________________________________________\r\n" + //
                "Bye. Hope to see you again soon!" + "\n" + //
                "____________________________________________________________\r\n");
  }

  /*
   * Displays a given message.
   * 
   * @param message The message to be displayed.
   */
  public void showMessage(String message) {
    System.out.println("____________________________________________________________\r\n" + //
                message + "\n" + //
                "____________________________________________________________\r\n");
  }

  /*
   * Displays an error message with an "OOPS!!!" prefix.
   * 
   * @param error The error message to be displayed.
   */
  public void showError(String error) {
    System.out.println("____________________________________________________________\r\n" + //
                "OOPS!!! " + error + "\n" + //
                "____________________________________________________________\r\n");
  }
}
