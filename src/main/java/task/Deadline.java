package task;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description, "D");
        this.by = by;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + by + ")";
    }
}