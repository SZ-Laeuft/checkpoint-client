package org.szlaeuft.checkpoint;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Controler {

    @FXML
    private void settings(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Einstellungen");
        alert.setHeaderText(null);
        alert.setContentText("Einstellungsdialog wäre hier.");
        alert.showAndWait();
    }

    @FXML
    private void debug_toggle(ActionEvent event) {
        Application.debugHandler.toggleDebug();
    }

    @FXML
    private void restart(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Neustart");
        alert.setHeaderText(null);
        alert.setContentText("Die Anwendung wird neu gestartet.");
        alert.showAndWait();

        // Echte Neustart-Logik müsstest du hier implementieren
        System.out.println("Neustart der Anwendung (nur simuliert)");
    }

    @FXML
    private void quit(ActionEvent event) {
        System.out.println("Beenden aufgerufen");
        System.exit(0);
    }
}
