package chatbot;

/**
 * The ChatBot class represents a simple chatbot application
 * that manages tasks such as ToDos, Deadlines, and Events.
 */
public class ChatBot {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructor to create a ChatBot instance.
     */
    public ChatBot() {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage("/data/helios.txt");
        parser = new Parser();
        tasks = storage.loadTasks();
    }

    /**
     * Processes user input and returns the chatbot's response.
     *
     * @param input The user input string.
     * @return The chatbot's response as a string.
     */
    public String getResponse(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return "Goodbye! Hope to see you again soon!";
        } else if (input.equalsIgnoreCase("list")) {
            return tasks.listTasks();
        } else if (input.startsWith("mark")) {
            try {
                int taskIndex = parser.getIndex(input);
                tasks.markTask(taskIndex);
                storage.saveTasks(tasks);
                return "Nice! I've marked this task as done:\n" +
                        tasks.getTask(taskIndex).getDescription();
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        } else if (input.startsWith("unmark")) {
            try {
                int taskIndex = parser.getIndex(input);
                tasks.unmarkTask(taskIndex);
                storage.saveTasks(tasks);
                return "OK, I've marked this task as not done yet:\n" +
                        tasks.getTask(taskIndex).getDescription();
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        } else if (input.startsWith("todo")) {
            try {
                String description = parser.getTodoDescription(input);
                tasks.addTask(new task.Todo(description));
                storage.saveTasks(tasks);
                return "Got it. I've added this task:\n" +
                        tasks.getTask(tasks.getSize() - 1).getDescription() +
                        "\nNow you have " + tasks.getSize() + " tasks in the list.";
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        } else if (input.startsWith("deadline")) {
            try {
                String[] parts = parser.getDeadlineParts(input);
                tasks.addTask(new task.Deadline(parts[0], parts[1]));
                storage.saveTasks(tasks);
                return "Got it. I've added this deadline:\n" +
                        tasks.getTask(tasks.getSize() - 1).getDescription() +
                        "\nNow you have " + tasks.getSize() + " tasks in the list.";
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        } else if (input.startsWith("event")) {
            try {
                String[] parts = parser.getEventParts(input);
                tasks.addTask(new task.Event(parts[0], parts[1], parts[2]));
                storage.saveTasks(tasks);
                return "Got it. I've added this event:\n" +
                        tasks.getTask(tasks.getSize() - 1).getDescription() +
                        "\nNow you have " + tasks.getSize() + " tasks in the list.";
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        } else if (input.startsWith("delete")) {
            try {
                int taskIndex = parser.getIndex(input);
                String taskDesc = tasks.getTask(taskIndex).getDescription();
                tasks.removeTask(taskIndex);
                storage.saveTasks(tasks);
                return "Noted. I've removed this task:\n" +
                        taskDesc + "\nNow you have " + tasks.getSize() + " tasks in the list.";
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        } else if (input.startsWith("find")) {
            try {
                String keyword = parser.getKeyword(input);
                return tasks.findTasks(keyword);
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        } else {
            return "I'm sorry, but I don't know what that means :-(";
        }
    }
}
