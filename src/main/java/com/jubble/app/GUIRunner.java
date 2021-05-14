package com.jubble.app;

import com.jubble.app.javafx.ControllerFX;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIRunner {
    private static Stage primaryStage;

    public static void run(Stage primaryStage) throws IOException {
        GUIRunner.primaryStage = primaryStage;
        ControllerFX controller = new ControllerFX();

        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);

        Parent root = FXMLLoader.load(GUIRunner.class.getResource("/graphic.fxml"));

        primaryStage.setTitle("Jubble");

        Scene scene = new Scene(root, 850, 478);
        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);

        primaryStage.setResizable(false);

        primaryStage.setMaximized(false);
        primaryStage.setFullScreen(false);
        primaryStage.show();
    }

    public static void stop() {
        primaryStage.setOnCloseRequest(e -> {
            ThreadRunner.stop();
            Platform.exit();
        });
    }
}
