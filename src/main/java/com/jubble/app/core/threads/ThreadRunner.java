package com.jubble.app.core.threads;

import com.jubble.app.core.utils.GameStarterUtil;
import com.jubble.app.core.Settings;
import com.jubble.app.core.components.Balance;
import com.jubble.app.core.components.generator.Generator;
import com.jubble.app.core.utils.GameProgress;
import com.jubble.app.core.utils.GameProgressHandler;

import java.io.IOException;
import java.util.Timer;
import java.util.stream.Collectors;

/** Wrapper to manage start/stop of GameValuesThread TimerTask. */
public class ThreadRunner {
  /** Contains each GameValuesThread TimerTask instantiated by run(). */
  private static final Timer valueTimer = new Timer();



  /** Starts instance of the timer GameValuesThread timer. */
  public static void run() {
    final int TIMER_REFRESH_RATE = 1000;
    final int TIMER_DELAY = 0;
    GameStarterUtil.setUp();
    valueTimer.schedule(new GameValuesThread(), TIMER_DELAY, TIMER_REFRESH_RATE);
    System.out.println("Game thread started");
  }

  /** Stops each timer task and save progress. */
  public static void stop() {
    // Stop timer tasks.
    valueTimer.cancel();
    // Purge cancelled timers.
    valueTimer.purge();
    // Save game progress.

    GameProgress progress =
        new GameProgress(
              Settings.getGenerators().stream()
                .map(Generator::getNumberOwned)
                .collect(Collectors.toList()),
            Balance.getPrimary());
    try {
      GameProgressHandler.saveGame(progress);
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("Game thread stopped");
  }
}
