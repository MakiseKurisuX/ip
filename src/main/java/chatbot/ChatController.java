package chatbot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import task.HeliosException;

/*
 * Controller class to manage chatbot user interface.
 * This class handles user input, chatbot responses, and updates the UI.
 */
public class ChatController {
    @FXML private AnchorPane root;
    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    private ChatBot chatbot;
    private Image userImage = new Image(getClass().getResourceAsStream("/images/user.jpg"));
    private Image botImage = new Image(getClass().getResourceAsStream("/images/bot.png"));

    /*
     * Initializes the controller after the FXML file is loaded.
     */
    @FXML
    public void initialize() throws HeliosException {
        try {
            chatbot = new ChatBot();
            scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
            String welcomeMessage = "Hello! I'm Ervin Chatbot!\r\n" + " What can I do for you?\r\n";
            dialogContainer.getChildren().add(new DialogBox(welcomeMessage, botImage, false));
        } catch (HeliosException e) {
            System.out.println("Error initializing chatbot: " + e.getMessage());
        }
    }

    /*
     * Handles user input when the send button is pressed.
     * Displays user message in the dialog container.
     * Retrieves chatbot response and displays it.
     * Clears the input field after processing.
     */
    @FXML
    private void handleUserInput() throws HeliosException {
        String input = userInput.getText();
        if (!input.trim().isEmpty()) {
            dialogContainer.getChildren().add(new DialogBox(input, userImage, true));
            String response = chatbot.getResponse(input);
            dialogContainer.getChildren().add(new DialogBox(response, botImage, false));
            scrollPane.vvalueProperty().unbind();
            scrollPane.setVvalue(1.0);
            userInput.clear();
        }
    }
}