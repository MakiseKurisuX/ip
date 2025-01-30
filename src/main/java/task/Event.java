package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Parser.DateTimeParser;

public class Event extends Task {
    private LocalDateTime  from;
    private LocalDateTime  to;

    public Event(String description, String from, String to) {
        super(description, "E");
        this.from = DateTimeParser.parseDateTime(from);  
        this.to = DateTimeParser.parseDateTime(to);
    }

    public String getFrom() {
        return DateTimeParser.stringDateTime(this.from);
    }

    public String getTo() {
        return DateTimeParser.stringDateTime(this.to);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (from: " + DateTimeParser.formatDateTime(this.from) + " to: " + DateTimeParser.formatDateTime(this.to) + ")";
    }
}