package com.jubble.app;

import com.jubble.app.javafx.ControllerFX;

import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUIRunner {
  /**
   * Initializes JavaFX scene and loads fxml file.
   *
   * @throws IOException when graphic.fxml is not found.
   */
  public static void run(Stage primaryStage) throws IOException {
    ControllerFX controller = new ControllerFX();

    FXMLLoader loader = new FXMLLoader();
    loader.setController(controller);

    Parent root = FXMLLoader.load(GUIRunner.class.getResource("/graphic.fxml"));

    primaryStage.setTitle("Jubble");

    Scene scene = new Scene(root, 850, 478);
    scene.getStylesheets().add("style.css");

    primaryStage.setScene(scene);
    primaryStage.getIcons().add(new Image(new File("src/main/resources/assets/logo.png").toURI().toString()));

    primaryStage.setResizable(false);

    primaryStage.setMaximized(false);
    primaryStage.setFullScreen(false);
    primaryStage.show();
  }

  public static void stop() {
    ThreadRunner.stop();
    Platform.exit();
  }
}
