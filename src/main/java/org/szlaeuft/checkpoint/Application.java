package org.szlaeuft.checkpoint;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.szlaeuft.checkpoint.managers.DebugManager;
import org.szlaeuft.checkpoint.managers.StateManager;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static DebugManager dm = new DebugManager();
    public static StateManager sm;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 480);
        stage.setResizable(false);
        stage.setScene(scene);

        sm = new StateManager(stage);
        dm.setStage(stage); //execute after stage&scene has been loaded as to be able to access both

        stage.show();

        sm.setCurrentState("idle", null);
        dm.setStateManager(sm); //pass the state manger to the debug manager for debug purposes
    }

    private static void checkForDebugFlag(String[] a){
        for(String i: a) {
            if(i.equals("--debug") || i.equals("-d")) {
                dm.setDebug(true);
                break;
            }
        }
    }

    public static void main(String[] args) {
        checkForDebugFlag(args);
        launch();
    }
}