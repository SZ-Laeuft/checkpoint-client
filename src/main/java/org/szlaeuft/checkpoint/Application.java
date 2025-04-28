package org.szlaeuft.checkpoint;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.szlaeuft.checkpoint.managers.DebugManager;
import org.szlaeuft.checkpoint.managers.StateManager;
import org.szlaeuft.checkpoint.nfc.NFCAdapter;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static DebugManager dm = new DebugManager();
    public static StateManager sm;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setFullScreen(true);

        //new NFCTest().start(sm);

        sm = new StateManager(stage);
        dm.setStage(stage); //execute after stage&scene has been loaded as to be able to access both

        stage.show();

        sm.setCurrentState("idle", null);
        dm.setStateManager(sm); //pass the state manger to the debug manager for debug purposes

        Platform.runLater(() -> {
            new NFCAdapter(sm);  // Start the NFCAdapter which will start the WebSocket server
        });

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