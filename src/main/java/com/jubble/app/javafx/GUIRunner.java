package com.jubble.app.javafx;

import com.jubble.app.core.threads.ThreadRunner;
import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Wrapper class that handles the GUI lifecycle.
 */
public final class GUIRunner {
  private GUIRunner() {}

  /**
   * Initializes JavaFX scene and loads fxml file.
   *
   * @param primaryStage JavaFX primary stage.
   * @throws IOException when graphic.fxml is not found.
   */
  public static void run(final Stage primaryStage) throws IOException {
    ControllerFX controller = new ControllerFX();

    FXMLLoader loader = new FXMLLoader();
    loader.setController(controller);

    Parent root = FXMLLoader.load(GUIRunner.class.getResource("/graphic.fxml"));

    primaryStage.setTitle("Jubble");

    Scene scene = new Scene(root, 850, 478);
    scene.getStylesheets().add("style.css");

    primaryStage.setScene(scene);
    primaryStage
        .getIcons()
        .add(new Image(new File("src/main/resources/assets/logo.png").toURI().toString()));

    primaryStage.setResizable(false);

    primaryStage.setMaximized(false);
    primaryStage.setFullScreen(false);
    primaryStage.show();
  }

  /**
   * Stop the GUI.
   */
  public static void stop() {
    ThreadRunner.stop();
    Platform.exit();
  }
}
