package chatbot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/*
 * Serves as the entry point for the Helios Task Manager ChatBot application.
 * Initializes and launches the JavaFX application, using an FXML layout.
 */
public class Main extends Application {

    /*
     * Starts the JavaFX application, loading the FXML layout.
     * 
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/chatbot.fxml"));
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Helios Task Manager");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * The main entry point for the JavaFX application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}