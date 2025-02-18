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
    private static final int IMAGE_SIZE = 50;
    private static final int TEXT_PADDING = 10;
    private static final int CORNER_RADIUS = 10;
    private static final int SPACING = 10;

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

        displayPicture.setFitWidth(IMAGE_SIZE);
        displayPicture.setFitHeight(IMAGE_SIZE);

        text.setWrapText(true);
        text.setMaxWidth(Double.MAX_VALUE);
        text.setPadding(new Insets(TEXT_PADDING));

        text.setBackground(new Background(new BackgroundFill(
                isUser ? Color.LIGHTBLUE : Color.LIGHTGRAY, new CornerRadii(CORNER_RADIUS), Insets.EMPTY
        )));

        if (isUser) {
            this.setAlignment(Pos.TOP_RIGHT);
            this.getChildren().addAll(text, displayPicture);
        } else {
            this.setAlignment(Pos.TOP_LEFT);
            this.getChildren().addAll(displayPicture, text);
        }

        this.setSpacing(SPACING);
    }
}