package chatbot;

import task.HeliosException;

/*
 * The Parser class handles the interpretation and extraction of user commands from input strings.
 */
public class Parser {

    /*
     * Extracts the command from user input.
     * 
     * @param task The full user input string.
     * @param The first word of the input, which is the command.
     */
    public String getCommand(String task) {
        return task.split(" ")[0];
    }

    /*
     * Extracts and returns the index from a user command.
     * 
     * @param task The full user input string.
     * @return The zero-based index extracted from the input.
     */
    public int getIndex(String task) {
        return Integer.parseInt(task.split(" ")[1]) - 1;
    }

    /*
     * Extracts the description of a Todo task from user input.
     * 
     * @param task The full user input string.
     * @return The extracted description of the task.
     * @throws HeliosException If the description is empty.
     */
    public String getTodoDescription(String task) throws HeliosException {
        if (task.length() <= 4) {
            throw new HeliosException("The description of a todo cannot be empty.");
        }
        return task.substring(5);
    }

    /*
     * Extracts the description and deadline time from a Deadline task.
     * 
     * @param task The full user input string.
     * @return A string array where [task description, deadline time]
     * @throws HeliosException If the description is empty.
     */
    public String[] getDeadlineParts(String task) throws HeliosException {
        String[] parts = task.substring(9).split(" /by ");
        if (parts.length == 1) {
            throw new HeliosException("The description of a deadline cannot be empty.");
        } else if (parts.length != 2) {
            throw new HeliosException("Deadline must have a /by time.");
        }
        return parts;
    }
    
    /*
     * Extracts the description, from time and to time from a Event task.
     * 
     * @param task The full user input string.
     * @return A string array where [task description, from time, to time]
     * @throws HeliosException If the description is empty.
     */
    public String[] getEventParts(String task) throws HeliosException {
        String[] parts = task.substring(6).split(" /from | /to ");
        if (parts.length == 1) {
            throw new HeliosException("The description of an event cannot be empty.");
        } else if (parts.length != 3) {
            throw new HeliosException("Event must have a /from and /to time.");
        } 
        return parts;
    }

    /*
     * Extracts the description of a Keyword from user input.
     * 
     * @param task The full user input string.
     * @return The extracted keyword.
     * @throws HeliosException If the description is empty.
     */
    public String getKeyword(String task) throws HeliosException {
        if (task.substring(5).equals("")) {
            throw new HeliosException("You must input a keyword.");
        }
        return task.substring(5);
    }

}
