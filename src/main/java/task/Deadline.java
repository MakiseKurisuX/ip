package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Parser.DateTimeParser;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, String by) {
        super(description, "D");
        this.by = DateTimeParser.parseDateTime(by);
    }

    public String getBy() {
        return DateTimeParser.stringDateTime(by);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + DateTimeParser.formatDateTime(by) + ")";
    }
}