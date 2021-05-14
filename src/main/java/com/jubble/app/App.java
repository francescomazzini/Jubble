package com.jubble.app;

import com.jubble.app.javafx.ControllerFX;
import com.jubble.app.utils.GameProgressHandler;
import com.jubble.app.utils.Settings;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main app class
 * */
public class App extends Application{

  @Override
  public void start(Stage primaryStage) throws Exception {
    GameProgressHandler.loadGame();

    Settings.getGenerators().get(0).incrementNumberOwned();

    ThreadRunner.run();
    GUIRunner.run(primaryStage);

    primaryStage.setOnCloseRequest(e -> {
      GUIRunner.stop();
    });
  }

  /**
   * Start game main loop
   * */
  public static void main(String[] args) {
    launch(args);
  }

}
