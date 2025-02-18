package chatbot;

import task.HeliosException;

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
    public ChatBot() throws HeliosException {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage("src/main/data/helios.txt");
        parser = new Parser();
        tasks = storage.loadTasks();
    }

    /**
     * Handles the "mark" command by marking a task as done.
     *
     * @param input The user command in the format "mark [index]".
     * @return A response message indicating whether the task was successfully marked.
     */
    private String handleMark(String input) {
        try {
            int taskIndex = parser.getIndex(input);
            assert taskIndex >= 0 && taskIndex < tasks.getSize() : "Task index out of bounds!";
            tasks.markTask(taskIndex);
            storage.saveTasks(tasks);
            assert tasks.getTask(taskIndex).getIsDone() : "Task should be marked as done!";
            return ui.showMarkMessage(tasks.getTask(taskIndex));
        } catch (Exception e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Handles the "unmark" command by marking a task as not done.
     *
     * @param input The user command in the format "unmark [index]".
     * @return A response message indicating whether the task was successfully unmarked.
     */
    private String handleUnmark(String input) {
        try {
            int taskIndex = parser.getIndex(input);
            assert taskIndex >= 0 && taskIndex < tasks.getSize() : "Task index out of bounds!";
            tasks.unmarkTask(taskIndex);
            storage.saveTasks(tasks);
            assert !tasks.getTask(taskIndex).getIsDone() : "Task should be marked as not done!";
            return ui.showUnmarkMessage(tasks.getTask(taskIndex));
        } catch (Exception e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Handles the "todo" command by adding a new ToDo task.
     *
     * @param input The user command in the format "todo [description]".
     * @return A response message confirming the addition of the task.
     */
    private String handleTodo(String input) {
        try {
            String description = parser.getTodoDescription(input);
            assert description != null && !description.isEmpty() : "Todo description cannot be null or empty!";
            int prevSize = tasks.getSize();
            tasks.addTask(new task.Todo(description));
            storage.saveTasks(tasks);
            assert tasks.getSize() == prevSize + 1 : "TaskList size should increase by 1!";
            return ui.showAddTaskMessage(tasks.getTask(tasks.getSize() - 1), tasks.getSize());
        } catch (Exception e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Handles the "deadline" command by adding a new Deadline task.
     *
     * @param input The user command in the format "deadline [description] /by [time]".
     * @return A response message confirming the addition of the deadline task.
     */
    private String handleDeadline(String input) {
        try {
            String[] parts = parser.getDeadlineParts(input);
            assert parts != null : "Parser returned null for deadline parts!";
            assert parts.length == 2 : "Deadline task must have exactly 2 parts (description and deadline).";
            int prevSize = tasks.getSize();
            tasks.addTask(new task.Deadline(parts[0], parts[1]));
            storage.saveTasks(tasks);
            assert tasks.getSize() == prevSize + 1 : "TaskList size did not increase after adding a deadline!";
            assert tasks.getTask(tasks.getSize() - 1) instanceof task.Deadline;
            return ui.showAddTaskMessage(tasks.getTask(tasks.getSize() - 1), tasks.getSize());
        } catch (Exception e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Handles the "event" command by adding a new Event task.
     *
     * @param input The user command in the format "event [description] /from [start] /to [end]".
     * @return A response message confirming the addition of the event task.
     */
    private String handleEvent(String input) {
        try {
            String[] parts = parser.getEventParts(input);
            assert parts != null : "Parser returned null for event parts!";
            assert parts.length == 3 : "Event task must have exactly 3 parts (description, from-time, to-time).";
            int prevSize = tasks.getSize();
            tasks.addTask(new task.Event(parts[0], parts[1], parts[2]));
            storage.saveTasks(tasks);
            assert tasks.getSize() == prevSize + 1 : "TaskList size did not increase after adding an event!";
            assert tasks.getTask(tasks.getSize() - 1) instanceof task.Event;
            return ui.showAddTaskMessage(tasks.getTask(tasks.getSize() - 1), tasks.getSize());
        } catch (Exception e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Handles the "delete" command by removing a task from the list.
     *
     * @param input The user command in the format "delete [index]".
     * @return A response message confirming the deletion of the task.
     */
    private String handleDelete(String input) {
        try {
            int taskIndex = parser.getIndex(input);
            assert taskIndex >= 0 && taskIndex < tasks.getSize() : "Task index out of bounds!";
            int prevSize = tasks.getSize();
            String taskDesc = tasks.getTask(taskIndex).getDescription();
            tasks.removeTask(taskIndex);
            storage.saveTasks(tasks);
            assert tasks.getSize() == prevSize - 1 : "TaskList size should decrease by 1!";
            return ui.showDeleteMessage(taskDesc, tasks.getSize());
        } catch (Exception e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Handles the "find" command by searching for tasks containing a given keyword.
     *
     * @param input The user command in the format "find [keyword]".
     * @return A list of matching tasks or an appropriate message if none are found.
     */
    private String handleFind(String input) {
        try {
            String keyword = parser.getKeyword(input);
            return tasks.findTasks(keyword);
        } catch (Exception e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Handles the "sort" command by sorting tasks chronologically.
     *
     * @return A response message indicating that tasks have been sorted.
     */
    private String handleSort() {
        try {
            tasks.sortTasks();
            storage.saveTasks(tasks);
            return ui.showSortMessage() + "\n" + tasks.listTasks();
        } catch (Exception e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Processes user input and returns the chatbot's response.
     *
     * @param input The user input string.
     * @return The chatbot's response as a string.
     */
    public String getResponse(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return ui.goodbyeMessage();
        } else if (input.equalsIgnoreCase("list")) {
            return tasks.listTasks();
        } else if (input.startsWith("mark")) {
            return handleMark(input);
        } else if (input.startsWith("unmark")) {
            return handleUnmark(input);
        } else if (input.startsWith("todo")) {
            return handleTodo(input);
        } else if (input.startsWith("deadline")) {
            return handleDeadline(input);
        } else if (input.startsWith("event")) {
            return handleEvent(input);
        } else if (input.startsWith("delete")) {
            return handleDelete(input);
        } else if (input.startsWith("find")) {
            return handleFind(input);
        } else if (input.equals("sort")) {
            return handleSort();
        } else {
            return ui.showErrorMessage("I'm sorry, but I don't know what that means :-(");
        }
    }
}
