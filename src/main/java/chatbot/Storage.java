package chatbot;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/*
 * Handles reading and writing of tasks to a storage file.
 * Has methods to load tasks from a file and another method to load list into a file.
 */
public class Storage {
    private String filePath;
    
    /*
     * Constructs a Storage object with a file path.
     * 
     * @param filePath The path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /*
     * Loads tasks from storage file and returns the tasks as a TaskList Object.
     * 
     * @return A TaskList Object containing all the tasks from the file.
     */
    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
        System.out.println(filePath);
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String currentLine = fileScanner.nextLine();
                String[] splittedLine = currentLine.split(" \\| ");
                if (splittedLine[0].equals("T")) {
                    tasks.addTask(new Todo(splittedLine[2]));
                } else if (splittedLine[0].equals("D")) {
                    tasks.addTask(new Deadline(splittedLine[2], splittedLine[3]));
                } else if (splittedLine[0].equals("E")) {
                    String[] fromToSplitted = splittedLine[3].split(" - ");
                    tasks.addTask(new Event(splittedLine[2], fromToSplitted[0], fromToSplitted[1]));
                }
                if (splittedLine[1].equals("1")) {
                    tasks.getTask(tasks.getSize() - 1).setIsDone(true);
                }
            }
        } catch (IOException e) {
            System.out.println("File does not exist. Creating File.");
        }
        return tasks;
    }

    /*
     * Saves the tasks from the given TaskList Object into the Storage File.
     * 
     * @param tasks The TaskList Object that contains tasks to be saved into Storage File.
     */
    public void saveTasks(TaskList tasks) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            ArrayList<Task> tasksArr = tasks.getTasks();
            for (Task task : tasksArr) {
                String taskType = task instanceof Todo ? "T" : task instanceof Deadline ? "D" : "E";
                String isDone = task.getIsDone() ? "1" : "0";
                String taskDetails = "";

                if (taskType.equals("T")) {
                    taskDetails = task.getPureDescription();
                } else if (taskType.equals("D")) {
                    Deadline deadline = (Deadline) task;
                    taskDetails = deadline.getPureDescription() + " | " + deadline.getBy();
                } else {
                    Event event = (Event) task;
                    taskDetails = event.getPureDescription() + " | " + event.getFrom() + " - " + event.getTo();
                }

                writer.println(taskType + " | " + isDone + " | " + taskDetails);
            }
        } catch (IOException e) {
                System.out.println("Error writing to ./data/helios.txt: " + e.getMessage());
        }
    }

}
