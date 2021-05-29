package com.jubble.app;

import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.utils.GameProgress;
import com.jubble.app.utils.GameProgressHandler;
import com.jubble.app.utils.Settings;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.stream.Collectors;

/** Wrapper to manage start/stop of GameValuesThread TimerTask. */
public class ThreadRunner {
  /** Contains Balance of the player. */
  private static Balance gameBalance;
  /** Contains each GameValuesThread TimerTask instantiated by run(). */
  private static final Timer valueTimer = new Timer();

  private static boolean isTimerActive = false;

  private static void useSavedValues(GameProgress progress) {
    System.out.println("Recovering game values.");
    gameBalance = new Balance();
    gameBalance.setPrimary(progress.getBalance());
    List<Integer> numberOwned = progress.getOwnedGenerators();
    for(int i = 0; i < Settings.getGenerators().size(); i++) {
      Settings.getGenerators().get(i).setNumberOwned(numberOwned.get(i));
    }
  }

  private static void startFromScratch() {
    System.out.println("No saved values fault. Starting from scratch.");
    gameBalance = new Balance();
    Settings.giftInitialAmount();
  }

  /** Starts instance of the timer GameValuesThread timer. */
  public static void run() {
    GameProgress progress = GameProgressHandler.loadGame();
    if (progress != null) useSavedValues(progress);
    else startFromScratch();
    valueTimer.schedule(new GameValuesThread(gameBalance), 0, 1000);
    isTimerActive = true;
    System.out.println("Game thread started");
  }

  /** Stops each timer task and save progress. */
  public static void stop() {
    // Stop timer tasks.
    valueTimer.cancel();
    // Purge cancelled timers.
    valueTimer.purge();
    isTimerActive = false;
    // Save game progress.

    GameProgress progress =
        new GameProgress(
            Settings.getGenerators().stream()
                .map(Generator::getNumberOwned)
                .collect(Collectors.toList()),
            gameBalance.getPrimary());
    try {
      GameProgressHandler.saveGame(progress);
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("Game thread stopped");
  }

  /**
   * Returns current balance.
   *
   * @return gameBalance balance of the player.
   */
  public static Balance getGameBalance() {
    return gameBalance;
  }

  /**
   * Returns true if timer is active, false otherwise.
   *
   * @return isTimerActive
   */
  public static boolean timerStatus() {
    return isTimerActive;
  }
}
