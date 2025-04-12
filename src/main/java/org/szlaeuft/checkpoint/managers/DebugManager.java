package org.szlaeuft.checkpoint.managers;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.szlaeuft.checkpoint.Application;
import org.szlaeuft.checkpoint.helpers.MessageHelper;

public class DebugManager {
    private Boolean debug = false;
    private Stage stage;

    private Boolean fullscreen = false;
    private String state = "loading";

    private StateManager sm;

    public Boolean isDebug() {
        return debug;
    }

    public void setDebug(Boolean state) {
        this.debug = state;
        evaluateChanges();
    }

    public void setStateManager(StateManager sm) {
        this.sm = sm;
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

        Button debug_fullscreen = (Button) stage.getScene().getRoot().lookup("#debug_fullscreen");
        Button debug_state = (Button) stage.getScene().getRoot().lookup("#debug_state");
        ImageView debug_image = (ImageView) stage.getScene().getRoot().lookup("#debug_indicator");

        if(debug){
            stage.setTitle("SZ-Läuft - Checkpoint - DEBUG MODE ACTIVE");
            stage.getScene().setCursor(Cursor.CLOSED_HAND);

            debug_fullscreen.setOpacity(1);
            debug_fullscreen.setCursor(Cursor.HAND);
            debug_state.setOpacity(1);
            debug_state.setCursor(Cursor.HAND);
            debug_image.setOpacity(1);
        }
        else{
            stage.setTitle("SZ-Läuft - Checkpoint");
            stage.getScene().setCursor(Cursor.NONE);

            debug_fullscreen.setOpacity(0);
            debug_fullscreen.setCursor(Cursor.NONE);
            debug_state.setOpacity(0);
            debug_state.setCursor(Cursor.NONE);
            debug_image.setOpacity(0);
        }
    }

    public void toggleFullscreen() {
        fullscreen = !fullscreen;
        if(fullscreen){
            stage.setFullScreen(true);
        }
        else{
            stage.setFullScreen(false);
        }
    }

    public void toggleState(){
        switch(state){
            case "idle":
                sm.setCurrentState(state,null);
                state = "loading";
                break;
            case "loading":
                sm.setCurrentState(state,null);
                state = "success";
                break;
            case "success":
                sm.setCurrentState(state,new MessageHelper("Raphael Graf-Andrasch", "Runde:", "20", "Zeit:", "03:34:52", "Bestzeit:", "02:54:12"));
                state = "error";
                break;
            case "error":
                sm.setCurrentState(state,null);
                state = "idle";
                break;
        }
    }

}
