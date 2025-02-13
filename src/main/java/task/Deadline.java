package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import chatbot.DateTimeParser;

/*
 * Represents a Deadline task, which has a due date and/or time.
 * Inherits from the Task class.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /*
     * Constructor for a Deadline task with a description and due date/time.
     * 
     * @param description The description of the deadline.
     * @param by The due date and time. Converted to a LocalDateTime object using DateTimeParser.
     */
    public Deadline(String description, String by) {
        super(description, "D");
        this.by = DateTimeParser.parseDateTime(by);
    }

    /*
     * Returns formatted  time of the deadline
     * 
     * @return String representation of the deadline time.
     */
    public String getBy() {
        return DateTimeParser.stringDateTime(by);
    }

    /*
     * Performs similar function as Task's getDescription.
     * Overriden to add time.
     * 
     * @return A string representing the deadline's details.
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + DateTimeParser.formatDateTime(by) + ")";
    }

    /**
     * Retrieves the due date and time of this deadline task.
     *
     * @return A LocalDateTime representing the deadline's due date and time.
     */
    @Override
    public LocalDateTime getSortKey() {
        return this.by;
    }

}