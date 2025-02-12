package chatbot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;

/*
 * Controller class to manage chatbot user interface.
 * This class handles user input, chatbot responses, and updates the UI.
 */
public class ChatController {
    @FXML private AnchorPane root;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    private ChatBot chatbot = new ChatBot();
    private Image userImage = new Image(getClass().getResourceAsStream("/images/user.jpg"));
    private Image botImage = new Image(getClass().getResourceAsStream("/images/bot.png"));

    /*
     * Initializes the controller after the FXML file is loaded.
     */
    @FXML
    public void initialize() {
        dialogContainer.heightProperty().addListener((obs, oldHeight, newHeight) -> 
            root.setPrefHeight(newHeight.doubleValue() + 50));
    }

    /*
     * Handles user input when the send button is pressed.
     * Displays user message in the dialog container.
     * Retrieves chatbot response and displays it.
     * Clears the input field after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (!input.trim().isEmpty()) {
            dialogContainer.getChildren().add(new DialogBox(input, userImage, true));
            String response = chatbot.getResponse(input);
            dialogContainer.getChildren().add(new DialogBox(response, botImage, false));
            userInput.clear();
        }
    }
}