package lexi.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        Rectangle clip = new Rectangle(
                displayPicture.getFitWidth(), // Use the width of the ImageView
                displayPicture.getFitHeight() // Use the height of the ImageView
        );
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }
    public static DialogBox getLexiDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }
    public static DialogBox getLexiGreeting(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
    private void changeDialogStyle(String commandType) {
        switch (commandType) {
        case "AddCommand" -> dialog.getStyleClass().add("add-label");
        case "MarkCommand" -> dialog.getStyleClass().add("marked-label");
        case "DeleteCommand" -> dialog.getStyleClass().add("delete-label");
        case "Error" -> dialog.getStyleClass().add("error-label");
        default -> {
            // Do nothing
        }
        }
    }
}
