package chatbot;

import task.HeliosException;

public class Parser {

  public String getCommand(String task) {
    return task.split(" ")[0];
  }

  public int getIndex(String task) {
    return Integer.parseInt(task.split(" ")[1]) - 1;
  }

  public String getTodoDescription(String task) throws HeliosException {
    if (task.length() <= 4) {
      throw new HeliosException("The description of a todo cannot be empty.");
    }
    return task.substring(5);
  }

  public String[] getDeadlineParts(String task) throws HeliosException {
    String[] parts = task.substring(9).split(" /by ");
    if (parts.length == 1) {
      throw new HeliosException("The description of a deadline cannot be empty.");
    } else if (parts.length != 2) {
      throw new HeliosException("Deadline must have a /by time.");
    }
    return parts;
  }
  
  public String[] getEventParts(String task) throws HeliosException {
    String[] parts = task.substring(6).split(" /from | /to ");
    if (parts.length == 1) {
      throw new HeliosException("The description of an event cannot be empty.");
    } else if (parts.length != 3) {
      throw new HeliosException("Event must have a /from and /to time.");
    } 
    return parts;
  }

}
