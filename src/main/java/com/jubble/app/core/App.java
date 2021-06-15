package com.jubble.app.core;

import com.jubble.app.core.threads.ThreadRunner;
import com.jubble.app.javafx.GUIRunner;
import javafx.application.Application;
import javafx.stage.Stage;

/** Main entry point of Javafx application */
public class App extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    ThreadRunner.run();
    GUIRunner.run(primaryStage);

    primaryStage.setOnCloseRequest(e -> GUIRunner.stop());
  }

  /** Start game main loop */
  public static void main(String[] args) {
    launch(args);
  }
}
