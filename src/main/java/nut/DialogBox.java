package nut;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

/**
 * A custom control representing a dialog box consisting of text and an avatar image.
 */
public class DialogBox extends HBox {

    private static final double AVATAR_SIZE = 44.0;
    private static final double AVATAR_BG_SIZE = 52.0;

    private final Label text;
    private final StackPane avatarContainer;

    private DialogBox(String s, Image i) {
        text = new Label(s);
        text.setWrapText(true);

        // Avatar image
        ImageView displayPicture = new ImageView(i);
        displayPicture.setFitWidth(AVATAR_SIZE);
        displayPicture.setFitHeight(AVATAR_SIZE);
        displayPicture.setPreserveRatio(true);

        // Clip to circle so the avatar is round
        Circle clip = new Circle(AVATAR_SIZE / 2, AVATAR_SIZE / 2, AVATAR_SIZE / 2);
        displayPicture.setClip(clip);

        // Background behind the avatar (circle-like container)
        avatarContainer = new StackPane(displayPicture);
        avatarContainer.setMinSize(AVATAR_BG_SIZE, AVATAR_BG_SIZE);
        avatarContainer.setPrefSize(AVATAR_BG_SIZE, AVATAR_BG_SIZE);
        avatarContainer.setMaxSize(AVATAR_BG_SIZE, AVATAR_BG_SIZE);

        // Soft background + rounded corners (works for circle-ish and square-ish styles)
        avatarContainer.setStyle(
                "-fx-background-color: rgba(255,255,255,0.6);"
                        + "-fx-background-radius: 999;"
                        + "-fx-border-radius: 999;"
                        + "-fx-border-color: rgba(0,0,0,0.08);"
                        + "-fx-border-width: 1;"
        );

        this.setSpacing(10);
        this.setPadding(new Insets(4, 6, 4, 6));

        // default layout = user (right side): text then avatar, aligned to right
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, avatarContainer);
    }

    /**
     * Flips the dialog box such that the avatar is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        Node first = this.getChildren().get(0);
        Node second = this.getChildren().get(1);
        this.getChildren().setAll(second, first);
    }

    /**
     * Returns a dialog box for the user (right side).
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Returns a dialog box for Nut (left side).
     */
    public static DialogBox getNutDialog(String s, Image i) {
        DialogBox db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}