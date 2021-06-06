package com.jubble.app;

import com.jubble.app.utils.GameProgressHandler;
import javafx.application.Application;
import javafx.stage.Stage;

/** Main app class */
public class App extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    GameProgressHandler.loadGame();

    ThreadRunner.run();
    GUIRunner.run(primaryStage);

    primaryStage.setOnCloseRequest(e -> GUIRunner.stop());
  }

  /** Start game main loop */
  public static void main(String[] args) {
    launch(args);
  }
}
