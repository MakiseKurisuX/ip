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
        storage = new Storage("src/main/data/helios.txt");
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
                assert taskIndex >= 0 && taskIndex < tasks.getSize() : "Task index out of bounds!";
                tasks.markTask(taskIndex);
                storage.saveTasks(tasks);
                assert tasks.getTask(taskIndex).getIsDone() : "Task should be marked as done!";
                return "Nice! I've marked this task as done:\n" +
                        tasks.getTask(taskIndex).getDescription();
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        } else if (input.startsWith("unmark")) {
            try {
                int taskIndex = parser.getIndex(input);
                assert taskIndex >= 0 && taskIndex < tasks.getSize() : "Task index out of bounds!";
                tasks.unmarkTask(taskIndex);
                storage.saveTasks(tasks);
                assert !tasks.getTask(taskIndex).getIsDone() : "Task should be marked as not done!";
                return "OK, I've marked this task as not done yet:\n" +
                        tasks.getTask(taskIndex).getDescription();
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        } else if (input.startsWith("todo")) {
            try {
                String description = parser.getTodoDescription(input);
                assert description != null && !description.isEmpty() : "Todo description cannot be null or empty!";
                int prevSize = tasks.getSize();
                tasks.addTask(new task.Todo(description));
                storage.saveTasks(tasks);
                assert tasks.getSize() == prevSize + 1 : "TaskList size should increase by 1!";
                return "Got it. I've added this task:\n" +
                        tasks.getTask(tasks.getSize() - 1).getDescription() +
                        "\nNow you have " + tasks.getSize() + " tasks in the list.";
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        } else if (input.startsWith("deadline")) {
            try {
                String[] parts = parser.getDeadlineParts(input);
                assert parts != null : "Parser returned null for deadline parts!";
                assert parts.length == 2 : "Deadline task must have exactly 2 parts (description and deadline).";
                int prevSize = tasks.getSize();
                tasks.addTask(new task.Deadline(parts[0], parts[1]));
                storage.saveTasks(tasks);
                assert tasks.getSize() == prevSize + 1 : "TaskList size did not increase after adding a deadline!";
                assert tasks.getTask(tasks.getSize() - 1) instanceof task.Deadline 
                    : "Last task should be a Deadline but isn't!";
                return "Got it. I've added this deadline:\n" +
                        tasks.getTask(tasks.getSize() - 1).getDescription() +
                        "\nNow you have " + tasks.getSize() + " tasks in the list.";
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        } else if (input.startsWith("event")) {
            try {
                String[] parts = parser.getEventParts(input);
                assert parts != null : "Parser returned null for event parts!";
                assert parts.length == 3 : "Event task must have exactly 3 parts (description, from-time, to-time).";
                int prevSize = tasks.getSize();
                tasks.addTask(new task.Event(parts[0], parts[1], parts[2]));
                storage.saveTasks(tasks);
                assert tasks.getSize() == prevSize + 1 : "TaskList size did not increase after adding an event!";
                assert tasks.getTask(tasks.getSize() - 1) instanceof task.Event 
                    : "Last task should be an Event but isn't!";
                return "Got it. I've added this event:\n" +
                        tasks.getTask(tasks.getSize() - 1).getDescription() +
                        "\nNow you have " + tasks.getSize() + " tasks in the list.";
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        } else if (input.startsWith("delete")) {
            try {
                int taskIndex = parser.getIndex(input);
                assert taskIndex >= 0 && taskIndex < tasks.getSize() : "Task index out of bounds!";
                int prevSize = tasks.getSize();
                String taskDesc = tasks.getTask(taskIndex).getDescription();
                tasks.removeTask(taskIndex);
                storage.saveTasks(tasks);
                assert tasks.getSize() == prevSize - 1 : "TaskList size should decrease by 1!";
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
        } else if (input.equals("sort")) {
            try {
                tasks.sortTasks();
                return "Tasks have been sorted!";
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        } else {
            return "I'm sorry, but I don't know what that means :-(";
        }
    }
}
