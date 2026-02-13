package nut;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
import java.util.Objects;

/**
 * Main application class for Nut task manager.
 * <p>
 * This class serves as the entry point for the JavaFX GUI application.
 * It is responsible for initializing the primary stage, loading FXML layouts,
 * loading custom fonts, and setting up the initial window configuration.
 * </p>
 */

public class Main extends Application { // JavaFX Application class

    private ScrollPane scrollPane;
    private VBox dialogContainer; // Vertical Box
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    // user avatar image
    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/Images/user.png")));
    // bot avatar image
    private final Image nutImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/Images/nut-bot-head.png")));
    // bot app icon
    private final Image logoImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/Images/nut-bot.png")));

    private final Nut nut = new Nut();

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public void start(Stage stage) {
        try {
            // Load Inter fonts
            Font.loadFont(Objects.requireNonNull(getClass().getResourceAsStream("/Fonts/Inter_18pt-Thin.ttf")), 14);
            Font.loadFont(Objects.requireNonNull(getClass().getResourceAsStream("/Fonts/Inter_18pt-Regular.ttf")), 14);
            Font.loadFont(Objects.requireNonNull(getClass().getResourceAsStream("/Fonts/Inter_18pt-Medium.ttf")), 14);
            Font.loadFont(Objects.requireNonNull(getClass().getResourceAsStream("/Fonts/Inter_18pt-SemiBold.ttf")), 14);
            Font.loadFont(Objects.requireNonNull(getClass().getResourceAsStream("/Fonts/Inter_18pt-Bold.ttf")), 14);
            Font.loadFont(Objects.requireNonNull(getClass().getResourceAsStream("/Fonts/Inter_18pt-Black.ttf")), 14);
            Font.loadFont(Objects.requireNonNull(getClass().getResourceAsStream("/Fonts/Inter_18pt-Italic.ttf")), 14);

            // Load app icon
            Image nutImage = new Image(Objects.requireNonNull(
                    this.getClass().getResourceAsStream("/Images/nut-bot-head.png")));
            stage.getIcons().add(nutImage);

            // Load FXML
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Create scene
            Scene scene = new Scene(ap);

            // Attach CSS
            scene.getStylesheets().add(
                    Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm()
            );

            stage.setScene(scene);

            // Inject the Nut instance into the controller
            fxmlLoader.<MainWindow>getController().setNut(nut);

            // Center the window on screen
            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            double width = 480;
            double height = 720;
            stage.setX(bounds.getMinX() + (bounds.getWidth() - width) / 2);
            stage.setY(bounds.getMinY() + (bounds.getHeight() - height) / 2);
            stage.setWidth(width);
            stage.setHeight(height);
            stage.setResizable(false);

            stage.setTitle("Nut");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}