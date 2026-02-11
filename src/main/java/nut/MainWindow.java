package nut;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nut.Task.NutException;

/**
 * Controller for the main GUI window.
 * This class handles user interactions with the chat interface, including
 * text input, button clicks, and displaying conversation messages.
 * It serves as the bridge between the UI components defined in MainWindow.fxml
 * and the application logic in the Nut class.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Nut nut;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image nutImage = new Image(this.getClass().getResourceAsStream("/images/nut-bot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Nut instance into the controller.
     * This method is called after the FXML is loaded to provide the controller
     * with access to the application logic. It also displays the initial greeting message.
     *
     * @param n The Nut instance to be used by this controller.
     */
    public void setNut(Nut n) {
        nut = n;

        // Make Nut speak first, no auto user dialog
        DialogBox nutDialog = DialogBox.getNutDialog("Hello! I'm Nut. What can I do for you? :)", nutImage);
        dialogContainer.getChildren().add(nutDialog);
    }

    /**
     * Handles user input from the text field.
     * This method is triggered when the user presses Enter or clicks the Send button.
     * It retrieves the user's message, processes it through Nut, and displays both
     * the user's message and Nut's response in the dialog container.
     */
    @FXML
    private void handleUserInput() throws NutException{
        String input = userInput.getText();
        String response = null;
        response = nut.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNutDialog(response, nutImage)
        );
        userInput.clear();
    }
}
