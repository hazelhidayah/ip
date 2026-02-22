package nut.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import nut.Nut;

import java.net.URL;

/**
 * Controller for the main GUI window.
 * <p>
 * This class handles user interactions with the chat interface, including
 * text input, button clicks, and displaying conversation messages.
 * </p>
 * It serves as the bridge between the UI components defined in MainWindow.fxml
 * and the application logic in the Nut class.
 */
public class MainWindow extends AnchorPane {
    private static final String SCROLLBAR_HIDDEN_CLASS = "scrollbar-hidden";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private MediaView backgroundView;

    private Nut nut;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/Images/user.png"));
    private Image nutImage = new Image(this.getClass().getResourceAsStream("/Images/nut-bot-head.png"));
    private MediaPlayer backgroundPlayer;
    private PauseTransition exitDelayTransition;

    @FXML
    public void initialize() {
        scrollPane.setPannable(true);
        backgroundView.fitWidthProperty().bind(widthProperty());
        backgroundView.fitHeightProperty().bind(heightProperty());
        setupBackgroundVideo();
        setupTrackpadScrolling();

        dialogContainer.heightProperty().addListener((obs, oldVal, newVal) -> {
            updateScrollbarVisibility();
            scrollPane.setVvalue(1.0);
        });
        scrollPane.viewportBoundsProperty().addListener((obs, oldVal, newVal) -> updateScrollbarVisibility());
        Platform.runLater(this::updateScrollbarVisibility);
        // Disables button only if there are 0 characters (whitespace counts as input)
        sendButton.disableProperty().bind(userInput.textProperty().isEmpty());
    }

    private void updateScrollbarVisibility() {
        double contentHeight = dialogContainer.getHeight();
        double viewportHeight = scrollPane.getViewportBounds().getHeight();
        boolean shouldShowScrollbar = contentHeight > viewportHeight + 0.5;

        if (shouldShowScrollbar) {
            scrollPane.getStyleClass().remove(SCROLLBAR_HIDDEN_CLASS);
            return;
        }
        if (!scrollPane.getStyleClass().contains(SCROLLBAR_HIDDEN_CLASS)) {
            scrollPane.getStyleClass().add(SCROLLBAR_HIDDEN_CLASS);
        }
    }

    private void setupTrackpadScrolling() {
        dialogContainer.addEventFilter(ScrollEvent.SCROLL, event -> {
            double contentHeight = dialogContainer.getBoundsInLocal().getHeight();
            double viewportHeight = scrollPane.getViewportBounds().getHeight();
            if (contentHeight <= viewportHeight) {
                return;
            }

            // Route trackpad/mouse-wheel deltas directly to the ScrollPane.
            double delta = event.getDeltaY() / (contentHeight - viewportHeight);
            double nextValue = clamp(scrollPane.getVvalue() - delta, 0.0, 1.0);
            scrollPane.setVvalue(nextValue);
            event.consume();
        });
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    private void setupBackgroundVideo() {
        URL resource = getClass().getResource("/Images/background.mp4");
        if (resource == null) {
            backgroundView.setVisible(false);
            return;
        }
        Media media = new Media(resource.toExternalForm());
        backgroundPlayer = new MediaPlayer(media);
        backgroundPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundPlayer.setMute(true);
        backgroundPlayer.setAutoPlay(true);
        backgroundView.setMediaPlayer(backgroundPlayer);
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

        // Make Nut speak first, then show a quick command guide.
        DialogBox greetingDialog = DialogBox.getNutDialog(nut.getWelcomeMessage(), nutImage);
        DialogBox guideDialog = DialogBox.getNutDialog(nut.getStartupGuideMessage(), nutImage);
        dialogContainer.getChildren().addAll(greetingDialog, guideDialog);
    }

    /**
     * Handles user input from the text field.
     * This method is triggered when the user presses Enter or clicks the Send button.
     * It retrieves the user's message, processes it through Nut, and displays both
     * the user's message and Nut's response in the dialog container.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = nut.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNutDialog(response, nutImage)
        );
        userInput.clear();
        if (nut.isExitRequested()) {
            userInput.setDisable(true);
            scheduleExitAfterDelay();
        }
    }

    private void scheduleExitAfterDelay() {
        if (exitDelayTransition != null) {
            exitDelayTransition.stop();
        }
        exitDelayTransition = new PauseTransition(Duration.millis(nut.getExitDelayMillis()));
        exitDelayTransition.setOnFinished(event -> Platform.exit());
        exitDelayTransition.play();
    }
}
