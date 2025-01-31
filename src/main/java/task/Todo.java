package task;

/*
 * Represents a Todo task, which is the most basic task.
 * Inherits from the Task Class.
 */
public class Todo extends Task {
    /*
     * Constructor for a Todo object with given description.
     * 
     * @param description Description of Todo task.
     */
    public Todo(String description) {
        super(description, "T");
    }
}