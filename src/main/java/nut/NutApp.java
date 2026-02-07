package nut;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Objects;

public class NutApp extends Application { // JavaFX Application class

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) {

        // app icon
        Image icon = new Image(Objects.requireNonNull(
                NutApp.class.getResourceAsStream("/nut-bot.png")));
        primaryStage.getIcons().add(icon); // app icon

        AnchorPane mainLayout = new AnchorPane(); // root node to arrange the different components
        mainLayout.setStyle("-fx-background-color: rgb(255, 209, 220);"); // scene with custom pink background

        // center the window
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
        scrollPane.setContent(dialogContainer);


        // text field
        userInput = new TextField();
        userInput.setPromptText("Type your message here...");


        // send button
        Text buttonText = new Text("Send");
        buttonText.setFont(Font.font("Inter", FontWeight.THIN, 14)); // custom font for sendButton
        sendButton = new Button(); // make button
        sendButton.setGraphic(buttonText); // add text to sendButton


        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        double padding = 10.0;
        double inputHeight = 30.0;

        AnchorPane.setTopAnchor(scrollPane, padding);
        AnchorPane.setLeftAnchor(scrollPane, padding);
        AnchorPane.setRightAnchor(scrollPane, padding);
        AnchorPane.setBottomAnchor(scrollPane, padding + inputHeight + padding);

        AnchorPane.setLeftAnchor(userInput, padding);
        AnchorPane.setBottomAnchor(userInput, padding);
        userInput.setPrefHeight(inputHeight);

        AnchorPane.setRightAnchor(sendButton, padding);
        AnchorPane.setBottomAnchor(sendButton, padding);
        sendButton.setPrefHeight(inputHeight);

        // text field takes remaining width beside the button
        userInput.prefWidthProperty().bind(
                mainLayout.widthProperty().subtract(sendButton.widthProperty()).subtract(padding * 3)
        );

        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        scene = new Scene(mainLayout, width, height);

        //        Rectangle rectangle = new Rectangle(440, 660); // rectangle of size 440x660
        //        rectangle.(bounds.getMinX() + (bounds.getWidth() - 440) / 2);
        //        rectangle.setY(bounds.getMinY() + (bounds.getHeight() - 660) / 2);
        //
        //        Text text = new Text(); // new Text object
        //        text.setFill(Color.BLACK);

        //        Image image = new Image("file:src/main/resources/nut-bot.png");
        //        ImageView imageView = new ImageView(image);
        //        imageView.setFitHeight(200); // set size of image
        //        imageView.setFitWidth(200);
        //        imageView.setPreserveRatio(true);

        //        root.getChildren().add(imageView); // add image to root
        primaryStage.setTitle("Nut"); // name of prog
        primaryStage.setScene(scene); // set the scene
        primaryStage.show();

    }
}