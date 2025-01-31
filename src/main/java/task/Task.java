package task;

/*
 * Represents a task with a description, completetion status and type.
 */
public class Task {
  
  protected String description;
  protected boolean isDone;
  protected String type;

  /*
   * Constructor for new Task Object.
   * 
   * @param description The task description.
   * @param type The task type.
   */
  public Task(String description, String type) {
      this.description = description;
      this.isDone = false;
      this.type = type;
  }

  /*
   * Returns the status icon of the task.
   * 
   * @return A string representing whether task is completed or not.
   */
  public String getStatusIcon() {
      return (isDone ? "X" : " "); 
  }

  /*
   * Returns the pure description of the task without additional formatting.
   * 
   * @return The task description.
   */
  public String getPureDescription () {
      return this.description;
  }

  /*
   * Returns the formatted description of the task.
   * 
   * @return The task description.
   */
  public String getDescription () {
      return "[" + this.type + "]" + "[" + getStatusIcon() + "] " + this.description;
  }

  /*
   * Returns the completion status of the task.
   * 
   * @return The task completion status.
   */
  public boolean getisDone() {
      return this.isDone;
  }

  /*
   * Returns the type of the task.
   * 
   * @return The task type.
   */
  public String getType() {
        return this.type;
  }

  /*
   * Sets the completion status of the task to either done or not done.
   */
  public void setisDone (boolean b) {
      this.isDone = b;
  }
}