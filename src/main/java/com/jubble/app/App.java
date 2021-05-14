package com.jubble.app;

import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.javafx.ControllerFX;
import com.jubble.app.utils.GameProgressHandler;
import com.jubble.app.utils.Settings;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.stream.Collectors;

/**
 * Main app class
 * */
public class App extends Application{

  @Override
  public void start(Stage primaryStage) throws Exception {
    GameProgressHandler.loadGame();

    Settings.getGenerators().get(0).incrementNumberOwned();

    ThreadManager.run();

    ControllerFX controller = new ControllerFX();

    FXMLLoader loader = new FXMLLoader();
    loader.setController(controller);

    Parent root = loader.load(getClass().getResource("/graphic.fxml"));

    primaryStage.setTitle("Jubble");

    Scene scene = new Scene(root, 850, 478);
    scene.getStylesheets().add("style.css");

    primaryStage.setScene(scene);

    primaryStage.setResizable(false);

    primaryStage.setMaximized(false);
    primaryStage.setFullScreen(false);
    primaryStage.show();

    primaryStage.setOnCloseRequest(e -> {
      ThreadManager.stop();
      Platform.exit();
    });

  }

  /**
   * Start game main loop
   * */
  public static void main(String[] args) {
    launch(args);
  }

}
