package com.jubble.app.javafx;

import com.jubble.app.core.threads.ThreadRunner;
import javafx.application.Application;
import javafx.stage.Stage;

/** Main entry point of Javafx application */
public final class App extends Application {

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
