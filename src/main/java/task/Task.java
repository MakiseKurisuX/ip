package task;

public class Task {
  protected String description;
  protected boolean isDone;
  protected String type;

  public Task(String description, String type) {
      this.description = description;
      this.isDone = false;
      this.type = type;
  }

  public String getStatusIcon() {
      return (isDone ? "X" : " "); 
  }

  public String getPureDescription () {
      return this.description;
  }

  public String getDescription () {
      return "[" + this.type + "]" + "[" + getStatusIcon() + "] " + this.description;
  }

  public boolean getisDone() {
      return this.isDone;
  }

  public void setisDone (boolean b) {
      this.isDone = b;
  }
}