package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import chatbot.DateTimeParser;

/*
 * Represents an Event Task, which has a start and end time.
 * Inherits from the Task Class.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /*
     * Constructor for an Event task with a description, start time and end time.
     * 
     * @param description Description of the event
     * @param from Start time of the event. Converted to a LocalDateTime object using DateTimeParser.
     * @param to End time of the event. Converted to a LocalDateTime object using DateTimeParser.
     */
    public Event(String description, String from, String to) {
        super(description, "E");
        this.from = DateTimeParser.parseDateTime(from);  
        this.to = DateTimeParser.parseDateTime(to);
    }

    /*
     * Returns formatted starting time of the event
     * 
     * @return String representation of the event starting time.
     */
    public String getFrom() {
        return DateTimeParser.stringDateTime(this.from);
    }

    /*
     * Returns formatted end time of the event
     * 
     * @return String representation of the event end time.
     */
    public String getTo() {
        return DateTimeParser.stringDateTime(this.to);
    }

    /*
     * Performs similar function as Task's getDescription.
     * Overriden to add start and end time.
     * 
     * @return A string representing the event's details.
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " (from: " + DateTimeParser.formatDateTime(this.from) + " to: " + DateTimeParser.formatDateTime(this.to) + ")";
    }

    /**
     * Retrieves the start date and time of this event task.
     *
     * @return A LocalDateTime representing the event's start date and time.
     */
    @Override
    public LocalDateTime getSortKey() {
        return this.from;
    }

    /**
     * Retrieves the end date and time of this event task.
     *
     * @return A LocalDateTime representing the event's end date and time.
     */
    public LocalDateTime getSortKey2() {
        return this.to;
    }

}