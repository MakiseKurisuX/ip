package chatbot;

import task.HeliosException;

/*
 * The Parser class handles the interpretation and extraction of user commands from input strings.
 */
public class Parser {

    private static final int TODO_DESCRIPTION_INDEX = 5;
    private static final int DEADLINE_DESCRIPTION_INDEX = 9;
    private static final int EVENT_DESCRIPTION_INDEX = 6;
    private static final int INDEX_INDEX = 1;
    private static final String SPACE_DELIMITER = " ";
    private static final String DEADLINE_DELIMITER = " /by ";
    private static final String EVENT_DELIMITER = " /from | /to ";
    private static final int EXPECTED_DEADLINE_PARTS = 2;
    private static final int EXPECTED_EVENT_PARTS = 3;
    private static final int KEYWORD_INDEX = 5;

    /*
     * Extracts and returns the index from a user command.
     * 
     * @param task The full user input string.
     * @return The zero-based index extracted from the input.
     */
    public int getIndex(String task) throws HeliosException {
        String[] parts = task.split(SPACE_DELIMITER);
        if (parts.length < INDEX_INDEX + 1) {
            throw new HeliosException("Invalid task index: Please use a valid index.");
        }
        return Integer.parseInt(parts[INDEX_INDEX]) - 1;
    }

    /*
     * Extracts the description of a Todo task from user input.
     * 
     * @param task The full user input string.
     * @return The extracted description of the task.
     * @throws HeliosException If the description is empty.
     */
    public String getTodoDescription(String task) throws HeliosException {
        if (task.length() <= TODO_DESCRIPTION_INDEX - 1) {
            throw new HeliosException("The description of a todo cannot be empty.");
        }
        String description = task.substring(TODO_DESCRIPTION_INDEX).trim();
        if (description.isEmpty()) {
            throw new HeliosException("The description of a todo cannot be empty.");
        }
        return description;
    }

    /*
     * Extracts the description and deadline time from a Deadline task.
     * 
     * @param task The full user input string.
     * @return A string array where [task description, deadline time]
     * @throws HeliosException If the description is empty.
     */
    public String[] getDeadlineParts(String task) throws HeliosException {
        if (task.length() <= DEADLINE_DESCRIPTION_INDEX - 1) {
            throw new HeliosException("The description of a deadline cannot be empty.");
        }
        String rawInput = task.substring(DEADLINE_DESCRIPTION_INDEX).trim();
        String[] parts = rawInput.split(DEADLINE_DELIMITER, 2);
        if (parts.length == 1) { 
            throw new HeliosException("The description of a deadline cannot be empty.");
        } else if (parts.length != EXPECTED_DEADLINE_PARTS) {
            throw new HeliosException("Deadline must have a /by time.");
        }
        if (parts[0].trim().isEmpty()) {
            throw new HeliosException("The description of a deadline cannot be empty.");
        }
        if (parts[1].trim().isEmpty()) {
            throw new HeliosException("The deadline time cannot be empty.");
        }
        return new String[]{parts[0].trim(), parts[1].trim()};
    }
    
    /*
     * Extracts the description, from time and to time from a Event task.
     * 
     * @param task The full user input string.
     * @return A string array where [task description, from time, to time]
     * @throws HeliosException If the description is empty.
     */
    public String[] getEventParts(String task) throws HeliosException {
        if (task.length() <= EVENT_DESCRIPTION_INDEX - 1) {
            throw new HeliosException("The description of an event cannot be empty.");
        }
        String rawInput = task.substring(EVENT_DESCRIPTION_INDEX).trim();
        String[] parts = rawInput.split(EVENT_DELIMITER, 3);
        if (parts.length == 1) { 
            throw new HeliosException("The description of an event cannot be empty.");
        } else if (parts.length != EXPECTED_EVENT_PARTS) {
            throw new HeliosException("Event must have a /from and /to time.");
        }
        if (parts[0].trim().isEmpty()) {
            throw new HeliosException("The description of an event cannot be empty.");
        }
        if (parts[1].trim().isEmpty()) {
            throw new HeliosException("The /from time cannot be empty.");
        }
        if (parts[2].trim().isEmpty()) {
            throw new HeliosException("The /to time cannot be empty.");
        }
        return new String[]{parts[0].trim(), parts[1].trim(), parts[2].trim()};
    }

    /*
     * Extracts the description of a Keyword from user input.
     * 
     * @param task The full user input string.
     * @return The extracted keyword.
     * @throws HeliosException If the description is empty.
     */
    public String getKeyword(String task) throws HeliosException {
        if (task.length() <= KEYWORD_INDEX - 1) {
            throw new HeliosException("You must input a keyword.");
        }
        String keyword = task.substring(KEYWORD_INDEX).trim();
        if (keyword.isEmpty()) {
            throw new HeliosException("You must input a keyword.");
        }
        return keyword;
    }

}
