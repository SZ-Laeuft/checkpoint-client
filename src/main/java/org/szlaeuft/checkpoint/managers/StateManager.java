package org.szlaeuft.checkpoint.managers;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.szlaeuft.checkpoint.helpers.MessageHelper;

public class StateManager {
    private Stage stage;
    private Scene scene;

    private ImageView status_icon;
    private Text title;

    private Text sub1_label;
    private Text sub2_label;
    private Text sub3_label;

    private Text sub1_value;
    private Text sub2_value;
    private Text sub3_value;

    public StateManager(Stage stage) {
        this.stage = stage;
        this.scene = stage.getScene();

        this.status_icon = (ImageView) scene.getRoot().lookup("#status_icon");

        this.title = (Text) scene.getRoot().lookup("#title");

        this.sub1_label = (Text) scene.getRoot().lookup("#sub1_label");
        this.sub2_label = (Text) scene.getRoot().lookup("#sub2_label");
        this.sub3_label = (Text) scene.getRoot().lookup("#sub3_label");

        this.sub1_value = (Text) scene.getRoot().lookup("#sub1_value");
        this.sub2_value = (Text) scene.getRoot().lookup("#sub2_value");
        this.sub3_value = (Text) scene.getRoot().lookup("#sub3_value");
    }

    public void setCurrentState(String state, MessageHelper message) {
        switch (state) {
            case "idle":
                status_icon.setImage(new Image(getClass().getResource("/icons/idle.png").toExternalForm()));

                message = new MessageHelper("Bereit zu Scannen!");
                displayMessage(message);
                break;

            case "loading":
                status_icon.setImage(new Image(getClass().getResource("/icons/spinner.gif").toExternalForm()));

                message = new MessageHelper("Bitte warten ...", null, "Server wird kontaktiert");
                displayMessage(message);
                break;

            case "success":
                if(message != null) {
                    status_icon.setImage(new Image(getClass().getResource("/icons/success.png").toExternalForm()));

                    message = new MessageHelper("Bereit zu Scannen!");
                    displayMessage(message);
                    break;
                }
                state = "error";
                message = new MessageHelper("Hoppala!", null, "Ein Fehler ist aufgetreten", null,null,"Error:", "SM0");


            case "error":
                status_icon.setImage(new Image(getClass().getResource("/icons/error.png").toExternalForm()));
                if(message == null) { message = new MessageHelper("Hoppala!", null, "Ein Fehler ist aufgetreten", null,null,"Error:", "0x0");}
                displayMessage(message);
                break;

            default:
                status_icon.setImage(new Image(getClass().getResource("/icons/error.png").toExternalForm()));
                if(message == null) { message = new MessageHelper("Hoppala!", null, "Ein Fehler ist aufgetreten", null,null,"Error:", "0x-1");}
                displayMessage(message);
                break;
        }
    }

    private void displayMessage(MessageHelper message) {
        title.setText(message.getTitle());
        sub1_label.setText(message.getSub1Label());
        sub2_label.setText(message.getSub2Label());
        sub3_label.setText(message.getSub3Label());
        sub1_value.setText(message.getSub1Value());
        sub2_value.setText(message.getSub2Value());
        sub3_value.setText(message.getSub3Value());
    }
}
