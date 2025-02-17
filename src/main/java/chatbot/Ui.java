package chatbot;
import java.util.Scanner;
import task.Task;

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

    /**
     * Displays welcome message.
     *
     * @return Welcome message string.
     */
    public String welcomeMessage() {
        return "Hello! I'm Ervin Chatbot!\nWhat can I do for you?";
    }

    /**
     * Displays goodbye message for when the user inputs 'bye'.
     *
     * @return Goodbye message string.
     */
    public String goodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     * @return Formatted message.
     */
    public String showMarkMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task.getDescription();
    }

    /**
     * Displays a message when a task is unmarked.
     *
     * @param task The task that was unmarked.
     * @return Formatted message.
     */
    public String showUnmarkMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task.getDescription();
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task The task that was added.
     * @param size The number of tasks in the list after addition.
     * @return Formatted message.
     */
    public String showAddTaskMessage(Task task, int size) {
        return "Got it. I've added this task:\n" + task.getDescription() +
               "\nNow you have " + size + " tasks.";
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param taskDesc The description of the task that was deleted.
     * @param size The number of tasks left in the list.
     * @return Formatted message.
     */
    public String showDeleteMessage(String taskDesc, int size) {
        return "Noted. I've removed this task:\n" + taskDesc +
               "\nNow you have " + size + " tasks.";
    }

    /**
     * Displays a message when tasks are sorted.
     *
     * @return Formatted message.
     */
    public String showSortMessage() {
        return "Tasks have been sorted!";
    }

    /**
     * Displays an error message.
     *
     * @param error The error message.
     * @return Formatted error message.
     */
    public String showErrorMessage(String error) {
        return "Error: " + error;
    }
}
