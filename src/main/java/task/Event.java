package task;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description, "E");
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (from: " + from + " to: " + to + ")";
    }
}