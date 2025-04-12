package org.szlaeuft.checkpoint;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DebugHandler {
    private Boolean debug = false;
    private Stage stage;

    public Boolean isDebug() {
        return debug;
    }

    public void setDebug(Boolean state) {
        this.debug = state;
        evaluateChanges();
    }

    public void toggleDebug() {
        debug = !debug;
        evaluateChanges();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        evaluateChanges();
    }

    private void evaluateChanges(){
        if(stage == null){
            System.out.println("Stage not yet loaded; unable to evaluate changes");
            return;
        }

        if(debug){
            stage.setTitle("SZ-Läuft - Checkpoint - DEBUG MODE ACTIVE");
            stage.setFullScreen(false);
            stage.getScene().setCursor(Cursor.CLOSED_HAND);

        }
        else{
            stage.setFullScreen(true);
            stage.setTitle("SZ-Läuft - Checkpoint");
            stage.getScene().setCursor(Cursor.NONE);
        }
    }


}
