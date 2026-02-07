package nut;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Objects;

@SuppressWarnings("unused")
public class Main extends Application { // JavaFX Application class

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    // user avatar image
    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/Images/user.png")));
    // bot avatar image
    private final Image nutImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/Images/nut-bot.png")));

    private final Nut nut = new Nut();

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public void start(Stage primaryStage) {

        // Load Inter fonts
        Font.loadFont(Objects.requireNonNull(getClass().getResourceAsStream("/Fonts/Inter_18pt-Thin.ttf")), 14);
        Font.loadFont(Objects.requireNonNull(getClass().getResourceAsStream("/Fonts/Inter_18pt-Regular.ttf")), 14);
        Font.loadFont(Objects.requireNonNull(getClass().getResourceAsStream("/Fonts/Inter_18pt-Medium.ttf")), 14);
        Font.loadFont(Objects.requireNonNull(getClass().getResourceAsStream("/Fonts/Inter_18pt-SemiBold.ttf")), 14);
        Font.loadFont(Objects.requireNonNull(getClass().getResourceAsStream("/Fonts/Inter_18pt-Bold.ttf")), 14);
        Font.loadFont(Objects.requireNonNull(getClass().getResourceAsStream("/Fonts/Inter_18pt-Black.ttf")), 14);
        Font.loadFont(Objects.requireNonNull(getClass().getResourceAsStream("/Fonts/Inter_18pt-Italic.ttf")), 14);

        primaryStage.getIcons().add(nutImage); // set app icon

        AnchorPane mainLayout = new AnchorPane(); // root node to arrange the different components
        mainLayout.setStyle("-fx-background-color: rgb(255, 209, 220);"); // pink background

        // center the window on screen
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double width = 480;
        double height = 720;
        primaryStage.setX(bounds.getMinX() + (bounds.getWidth() - width) / 2);
        primaryStage.setY(bounds.getMinY() + (bounds.getHeight() - height) / 2);
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setResizable(false);

        // Setting up required components
        scrollPane = new ScrollPane(); // scroll pane to display messages
        dialogContainer = new VBox(); // container to hold messages
        dialogContainer.setSpacing(10);
        scrollPane.setContent(dialogContainer);

        // text field for user input
        userInput = new TextField();
        userInput.setPromptText("Type your message here...");

        // send button
        sendButton = new Button("Send"); // create button node
        sendButton.getStyleClass().add("font-thin"); // uses the CSS styling rule

        // Handling user input
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Nut speaks first (no auto user dialog)
        DialogBox nutDialog = DialogBox.getNutDialog("Hello! I'm Nut. What can I do for you?", nutImage);
        dialogContainer.getChildren().add(nutDialog);

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        double padding = 10.0;
        double inputHeight = 30.0;

        // position the scroll pane with padding on all sides except the bottom
        AnchorPane.setTopAnchor(scrollPane, padding);
        AnchorPane.setLeftAnchor(scrollPane, padding);
        AnchorPane.setRightAnchor(scrollPane, padding);
        AnchorPane.setBottomAnchor(scrollPane, padding + inputHeight + padding);

        // position the text field at bottom left
        AnchorPane.setLeftAnchor(userInput, padding);
        AnchorPane.setBottomAnchor(userInput, padding);
        userInput.setPrefHeight(inputHeight);

        // position send button at bottom right
        AnchorPane.setRightAnchor(sendButton, padding);
        AnchorPane.setBottomAnchor(sendButton, padding);
        sendButton.setPrefHeight(inputHeight);
        sendButton.setPrefWidth(80.0);

        // text field takes remaining width beside the button
        userInput.prefWidthProperty().bind(
                mainLayout.widthProperty().subtract(sendButton.widthProperty()).subtract(padding * 3)
        );

        // configure scroll pane behavior
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0); // scroll to bottom

        // configure dialog container size
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        scene = new Scene(mainLayout, width, height);

        // attach CSS AFTER the scene is created
        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm()
        );

        primaryStage.setTitle("Nut"); // name of program
        primaryStage.setScene(scene); // set the scene
        primaryStage.show();
    }

    /**
     * Adds user + Nut dialog boxes to the dialog container and clears the input.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        if (userText == null || userText.isBlank()) {
            userInput.clear();
            return;
        }

        String nutText = nut.getResponse(userText);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),   // user on the right
                DialogBox.getNutDialog(nutText, nutImage)       // nut on the left
        );

        userInput.clear();
    }
}