package com.jubble.app;

import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.components.NewSettings;
import com.jubble.app.utils.GameProgress;
import com.jubble.app.utils.GameProgressHandler;
import com.jubble.app.utils.Settings;
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
    for (int i = 0; i < NewSettings.getGenerators().size(); i++) {
      NewSettings.getGenerators().get(i).setNumberOwned(numberOwned.get(i));
    }
  }

  private static void startFromScratch() {
    System.out.println("No saved values fault. Starting from scratch.");
    Settings.giftInitialAmount();
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
            NewSettings.getGenerators().stream()
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
