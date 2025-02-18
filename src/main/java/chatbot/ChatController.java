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
    private static final String WELCOME_MESSAGE = "Hello! I'm Helios Chatbot!\nWhat can I do for you?\n";
    private static final String USER_IMAGE_PATH = "/images/user.jpg";
    private static final String BOT_IMAGE_PATH = "/images/bot.png";

    private static final double SCROLL_TO_BOTTOM = 1.0;

    @FXML private AnchorPane root;
    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    private ChatBot chatbot;
    private Image userImage = new Image(getClass().getResourceAsStream(USER_IMAGE_PATH));
    private Image botImage = new Image(getClass().getResourceAsStream(BOT_IMAGE_PATH));

    /*
     * Initializes the controller after the FXML file is loaded.
     */
    @FXML
    public void initialize() throws HeliosException {
        try {
            chatbot = new ChatBot();
            scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
            dialogContainer.getChildren().add(new DialogBox(WELCOME_MESSAGE, botImage, false));
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
            scrollPane.setVvalue(SCROLL_TO_BOTTOM);
            userInput.clear();
        }
    }
}