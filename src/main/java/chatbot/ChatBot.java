package chatbot;

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

/*
 * The ChatBot class represents a simple chatbot application
 * that manages tasks such as ToDos, Deadlines and Events.
 */
public class ChatBot {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /*
     * Constructor to create a ChatBot instance.
     * Initializes user interface, task list, storage, and parser.
     */
    public ChatBot() {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage("../data/helios.txt");
        parser = new Parser();
    }

    /*
     * Runs the chatbot, handles user input and executes commands in a loop.
     * The chatbot will run commands until the user types 'bye'.
     */
    public void run() {
        Scanner in = new Scanner(System.in);

        tasks = storage.loadTasks();

        ui.welcomeMessage();

        while (true) {
            String task = in.nextLine();
            if (task.equals("bye")) {
                ui.goodbyeMessage();
                break;
            } else if (task.equals("list")) {
                ui.showMessage(tasks.listTasks());
            } else if (task.startsWith("mark")) {
                try {
                    int taskIndex = parser.getIndex(task);
                    tasks.markTask(taskIndex);
                    ui.showMessage("Nice! I've marked this task as done:\r\n    " +
                            tasks.getTask(taskIndex).getDescription());
                } catch (Exception e) {
                    ui.showError(e.getMessage());
                }
            } else if (task.startsWith("unmark")) {
                try {
                            int taskIndex = parser.getIndex(task);
                    tasks.unmarkTask(taskIndex);
                    ui.showMessage("OK, I've marked this task as not done yet:\r\n    " +
                            tasks.getTask(taskIndex).getDescription());
                } catch (Exception e) {
                    ui.showError(e.getMessage());
                }
            } else if (task.startsWith("todo")) {
                try {
                    String description = parser.getTodoDescription(task);
                    tasks.addTask(new Todo(description));
                    ui.showMessage("Got it. I've added this task:\r\n    " +
                            tasks.getTask(tasks.getSize() - 1).getDescription() +
                            "\nNow you have " + tasks.getSize() + " tasks in the list.\r");
                } catch (Exception e) {
                    ui.showError(e.getMessage());
                }
            } else if (task.startsWith("deadline")) {
                try {
                    String[] parts = parser.getDeadlineParts(task);
                    tasks.addTask(new Deadline(parts[0], parts[1]));
                    ui.showMessage("Got it. I've added this task:\r\n    " +
                            tasks.getTask(tasks.getSize() - 1).getDescription() +
                            "\nNow you have " + tasks.getSize() + " tasks in the list.\r");
                } catch (Exception e) {
                    ui.showError(e.getMessage());
                }
            } else if (task.startsWith("event")) {
                try {
                    String[] parts = parser.getEventParts(task);
                    tasks.addTask(new Event(parts[0], parts[1], parts[2]));
                    ui.showMessage("Got it. I've added this task:\r\n    " +
                            tasks.getTask(tasks.getSize() - 1).getDescription() +
                            "\nNow you have " + tasks.getSize() + " tasks in the list.\r");
                } catch (Exception e) {
                    ui.showError(e.getMessage());
                }
            } else if (task.startsWith("delete")) {
                try {
                    int taskIndex = parser.getIndex(task);
                    ui.showMessage("Noted. I've removed this task:\r\n    " +
                            tasks.getTask(taskIndex).getDescription() +
                            "\nNow you have " + (tasks.getSize() - 1) + " tasks in the list.\r");
                    tasks.removeTask(taskIndex);
                } catch (Exception e) {
                    ui.showError(e.getMessage());
                }
            } else if (task.startsWith("find")) {
                try {
                    String keyword = parser.getKeyword(task);
                    String returnedTasks = tasks.findTasks(keyword);
                    ui.showMessage(returnedTasks);
                } catch (Exception e) {
                    ui.showError(e.getMessage());
                }
            } else {
                ui.showError("I'm sorry, but I don't know what that means :-(");
            }

            storage.saveTasks(tasks);
        }
    }

    /*
     * The main entry point of Chatbot application.
     * Creates an instance of ChatBot object and starts the application.
     */
    public static void main(String[] args) {
        new ChatBot().run();
    }
}