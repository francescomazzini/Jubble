package com.jubble.app;

import com.jubble.app.classes.Balance;
import com.jubble.app.classes.Generator;
import com.jubble.app.utils.SaverLoader;
import com.jubble.app.utils.Settings;
import java.util.Timer;
import java.util.stream.Collectors;

/** Main app class */
public class App {

  private static boolean running = true;

  /** Start game main loop */
  public static void main(String[] args) {

    SaverLoader.loadGame();

    Balance gameBalance = new Balance();
    Settings.getGenerators().get(0).incrementNumberOwned();
    MainThread game = new MainThread(gameBalance);
    Thread t1 = new Thread(game);
    Timer timer = new Timer();
    t1.start();
    timer.schedule(new IncrementValues(gameBalance), 0, 1000);

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

    SaverLoader.saveGame(
        Settings.getGenerators().stream()
            .mapToInt(Generator::getNumberOwned)
            .boxed()
            .collect(Collectors.toList()),
        gameBalance.getPrimary());
  }

  public static void stop() {
    running = false;
  }
}
