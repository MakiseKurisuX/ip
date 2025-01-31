package task;

/*
 * Represents a custom exception for Chatbot application.
 */
public class HeliosException extends Exception {
  /*
   * Constructor for a new HeliosException with specified error message.
   * 
   * @param message The error message.
   */
  public HeliosException (String message) {
    super(message);
  }
}
