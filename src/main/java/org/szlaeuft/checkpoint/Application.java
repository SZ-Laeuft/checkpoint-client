package org.szlaeuft.checkpoint;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static DebugHandler debugHandler = new DebugHandler();

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 480);
        stage.setResizable(false);
        stage.setScene(scene);

        debugHandler.setStage(stage); //execute after stage&scene has been loaded as to be able to access both
        stage.show();
    }

    private static void checkForDebugFlag(String[] a){
        for(String i: a) {
            if(i.equals("--debug") || i.equals("-d")) {
                debugHandler.setDebug(true);
                break;
            }
        }
    }

    public static void main(String[] args) {
        checkForDebugFlag(args);
        launch();
    }
}