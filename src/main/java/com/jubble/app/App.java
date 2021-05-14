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

  private static Balance gameBalance;

  @Override
  public void start(Stage primaryStage) throws Exception {
    gameBalance = new Balance();

    GameProgressHandler.loadGame();

    Settings.getGenerators().get(0).incrementNumberOwned();

    ControllerFX controller = new ControllerFX();

    FXMLLoader loader = new FXMLLoader();
    loader.setController(controller);

    Parent root = loader.load(getClass().getResource("/graphic.fxml"));

    Timer timer = new Timer();
    timer.schedule(new GameValuesThread(gameBalance), 0, 1000);

    primaryStage.setTitle("Jubble");

    Scene scene = new Scene(root, 850, 478);
    scene.getStylesheets().add("style.css");

    primaryStage.setScene(scene);

    primaryStage.setResizable(false);

    primaryStage.setMaximized(false);
    primaryStage.setFullScreen(false);
    primaryStage.show();

    primaryStage.setOnCloseRequest(e -> {
      timer.cancel();
      timer.purge();
      //t1.interrupt();
      stopThreads();
      Platform.exit();
    });

  }


  /**
   * Start game main loop
   * */
  public static void main(String[] args) {

    launch(args);

  }


  public static void stopThreads() {
    GameProgressHandler.saveGame(
        Settings.getGenerators().stream()
            .mapToInt(Generator::getNumberOwned)
            .boxed()
            .collect(Collectors.toList()),
        gameBalance.getPrimary());

  }

  public static Balance getGameBalance () {
    return gameBalance;
  }


}
