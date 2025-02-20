package chatbot;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.HeliosException;
import task.Task;
import task.Todo;

/*
 * Handles reading and writing of tasks to a storage file.
 * Has methods to load tasks from a file and another method to load list into a file.
 */
public class Storage {
    private static final String TODO_MARKER = "T";
    private static final String DEADLINE_MARKER = "D";
    private static final String EVENT_MARKER = "E";
    private static final String DONE_MARKER = "1";
    private static final String INCOMPLETE_MARKER = "0";
    private static final String FIELD_DELIMITER_REGEX = " \\| ";
    private static final String PRINT_FIELD_DELIMITER = " | ";
    private static final String TIME_DELIMITER = " - ";
    
    private String filePath;
    
    /*
     * Constructs a Storage object with a file path.
     * 
     * @param filePath The path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates the parent directory for the file specified by {@code filePath} if it does not exist.
     */
    private void createDirectoryIfNotExists() {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (parentDir.mkdirs()) {
                System.out.println("Directory created successfully.");
            } else {
                System.err.println("Failed to create directory.");
            }
        }
    }

    /*
     * Loads tasks from storage file and returns the tasks as a TaskList Object.
     * 
     * @return A TaskList Object containing all the tasks from the file.
     */
    public TaskList loadTasks() throws HeliosException {
        TaskList tasks = new TaskList();
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String currentLine = fileScanner.nextLine();
                String[] lineParts = currentLine.split(FIELD_DELIMITER_REGEX);
                if (lineParts[0].equals(TODO_MARKER)) {
                    tasks.addTask(new Todo(lineParts[2]));
                } else if (lineParts[0].equals(DEADLINE_MARKER)) {
                    tasks.addTask(new Deadline(lineParts[2], lineParts[3]));
                } else if (lineParts[0].equals(EVENT_MARKER)) {
                    String[] timeParts = lineParts[3].split(TIME_DELIMITER);
                    tasks.addTask(new Event(lineParts[2], timeParts[0], timeParts[1]));
                } else {
                    System.err.println("Unknown task type: " + lineParts[0]);
                }
                if (lineParts[1].equals(DONE_MARKER)) {
                    tasks.getTask(tasks.getSize() - 1).setIsDone(true);
                }
            }
        } catch (IOException e) {
            System.out.println("File does not exist. Creating File.");
            createDirectoryIfNotExists();
        }
        return tasks;
    }

    /**
     * Saves the tasks from the given TaskList object into the storage file.
     * 
     * @param tasks The TaskList object that contains tasks to be saved into the file.
     */
    public void saveTasks(TaskList tasks) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            ArrayList<Task> allTasks = tasks.getTasks();
            for (Task task : allTasks) {
                String taskType = task instanceof Todo ? TODO_MARKER : task instanceof Deadline ? DEADLINE_MARKER : EVENT_MARKER;
                String completionStatus = task.getIsDone() ? DONE_MARKER : INCOMPLETE_MARKER;
                String taskDetails = "";

                if (taskType.equals(TODO_MARKER)) {
                    taskDetails = task.getPureDescription();
                } else if (taskType.equals(DEADLINE_MARKER)) {
                    Deadline deadline = (Deadline) task;
                    taskDetails = deadline.getPureDescription() + PRINT_FIELD_DELIMITER + deadline.getBy();
                } else if (taskType.equals(EVENT_MARKER)) {
                    Event event = (Event) task;
                    taskDetails = event.getPureDescription() + PRINT_FIELD_DELIMITER + event.getFrom() + TIME_DELIMITER + event.getTo();
                }

                writer.println(taskType + PRINT_FIELD_DELIMITER + completionStatus + PRINT_FIELD_DELIMITER + taskDetails);
            }
        } catch (IOException e) {
            System.out.println("Error writing to ./data/helios.txt: " + e.getMessage());
        }
    }

}
