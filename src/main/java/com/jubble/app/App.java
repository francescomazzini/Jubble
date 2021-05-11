package com.jubble.app;

import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.utils.SaverLoader;
import com.jubble.app.utils.Settings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.stream.Collectors;

/**
 * Main app class
 * */
public class App extends Application{

  private static boolean running = true;
  private static Balance gameBalance;

  @Override
  public void start(Stage primaryStage) throws Exception {

/*

    gameBalance = new Balance();

      SaverLoader.loadGame();

    Settings.getGenerators().get(0).incrementNumberOwned();
    MainThread game = new MainThread(gameBalance);

    Thread t1 = new Thread(game);
    Timer timer = new Timer();
    t1.start();
    timer.schedule(new IncrementValues(gameBalance), 0, 1000);
*/
  //  Parent root = FXMLLoader.load(getClass().getResource("/../resources/graphic.fxml"));

    primaryStage.setTitle("Jubble");
    primaryStage.setScene(new Scene(new FlowPane(),500,500));
    primaryStage.show();

  }


  /**
   * Start game main loop
   * */
  public static void main(String[] args) {

    launch(args);

    /*

    while (true) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      if (!running) break;
    }

    timer.cancel();
    timer.purge();
    t1.interrupt();

<<<<<<< HEAD
    SaverLoader.saveGame(Settings.getGenerators().stream()
                            .mapToInt(Generator::getNumberOwned)
                            .boxed()
                            .collect(Collectors.toList()), gameBalance.getPrimary());
 */
  }

  public static void stopThreads() {
    SaverLoader.saveGame(
        Settings.getGenerators().stream()
            .mapToInt(Generator::getNumberOwned)
            .boxed()
            .collect(Collectors.toList()),
        gameBalance.getPrimary());
    running = false;
  }


}
