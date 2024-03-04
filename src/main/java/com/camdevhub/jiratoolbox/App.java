package com.camdevhub.jiratoolbox;

import java.io.IOException;

import com.camdevhub.jiratoolbox.utils.JFXUtils;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(JFXUtils.loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(JFXUtils.loadFXML(fxml));
    }

    public static void main(String[] args) {
        launch();
    }

}