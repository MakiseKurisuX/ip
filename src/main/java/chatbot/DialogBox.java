package chatbot;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/*
 * Represents dialog box in the UI, displaying chat messages along with profile picture.
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /*
     * Constructs a DialogBox with given message, image and checks if message is by chatbot or sender.
     * 
     * @param message The message to be displayed in the dialog box.
     * @param img The image representing either the user or the chatbot.
     * @param isUser Boolean indicating whether message is from user or the chatbot.
     */
    public DialogBox(String message, Image img, boolean isUser) {
        assert message != null && !message.isEmpty() : "Message cannot be null or empty!";
        text = new Label(message);
        displayPicture = new ImageView(img);

        displayPicture.setFitWidth(50);
        displayPicture.setFitHeight(50);

        text.setWrapText(true);
        text.setMaxWidth(Double.MAX_VALUE);
        text.setPadding(new Insets(10));

        text.setBackground(new Background(new BackgroundFill(
                isUser ? Color.LIGHTBLUE : Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY
        )));

        if (isUser) {
            this.setAlignment(Pos.TOP_RIGHT);
            this.getChildren().addAll(text, displayPicture);
        } else {
            this.setAlignment(Pos.TOP_LEFT);
            this.getChildren().addAll(displayPicture, text);
        }

        this.setSpacing(10);
    }
}