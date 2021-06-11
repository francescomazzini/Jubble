package com.jubble.app.core.threads;

import com.jubble.app.core.Settings;
import com.jubble.app.core.components.Balance;
import com.jubble.app.core.components.generator.Generator;
import com.jubble.app.core.utils.GameActions;
import com.jubble.app.core.utils.GameProgress;
import com.jubble.app.core.utils.GameProgressHandler;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.stream.Collectors;

/** Wrapper to manage start/stop of GameValuesThread TimerTask. */
public class ThreadRunner {
  /** Contains each GameValuesThread TimerTask instantiated by run(). */
  private static final Timer valueTimer = new Timer();

  private static void useSavedValues(GameProgress progress) {
    System.out.println("Recovering game values.");
    Balance.setPrimary(progress.getBalance());
    List<Integer> numberOwned = progress.getOwnedGenerators();
    for (int i = 0; i < Settings.getGenerators().size(); i++) {
      Settings.getGenerators().get(i).setNumberOwned(numberOwned.get(i));
    }
  }

  private static void startFromScratch() {
    System.out.println("No saved values fault. Starting from scratch.");
    GameActions.giftInitialAmount();
  }

  /** Starts instance of the timer GameValuesThread timer. */
  public static void run() {
    GameProgress progress = GameProgressHandler.loadGame();
    if (progress != null) useSavedValues(progress);
    else startFromScratch();
    valueTimer.schedule(new GameValuesThread(), 0, 1000);
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
