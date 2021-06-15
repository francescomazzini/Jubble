package com.jubble.app.core.progress;

import com.jubble.app.core.GameActions;
import com.jubble.app.core.Settings;
import com.jubble.app.core.resources.Balance;
import java.io.IOException;
import java.util.List;

public final class GameStarterUtil {
  private GameStarterUtil() {}

  private static void recoverGameValues(final GameProgress progress) {
    System.out.println("Recovering game values.");
    Balance.setPrimary(progress.getBalance());
    List<Integer> numberOwned = progress.getOwnedGenerators();
    for (int i = 0; i < Settings.getGeneratorList().size(); i++) {
      Settings.getGeneratorList().get(i).setNumberOwned(numberOwned.get(i));
    }
  }

  private static void startFromScratch() {
    System.out.println("No saved values fault. Starting from scratch.");
    GameActions.giftInitialAmount();
  }

  /** Restore game progress if present. Otherwise start with default values. */
  public static void load() {
    try {
      final GameProgress savedProgress = GameProgressSerializer.loadGame();
      recoverGameValues(savedProgress);
    } catch (IOException e) {
      System.out.println(e.getMessage());
      startFromScratch();
    }
  }

  /** Save the game state into a file. */
  public static void save() {
    final GameProgress progress =
        new GameProgress(GameActions.getListOfGeneratorsNumberOwned(), Balance.getPrimary());
    try {
      GameProgressSerializer.saveGame(progress);
    } catch (IOException e) {
      throw new RuntimeException("Progress not saved. Player lost the progress.");
    }
  }
}
