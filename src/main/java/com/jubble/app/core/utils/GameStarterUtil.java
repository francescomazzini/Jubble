package com.jubble.app.core.utils;

import com.jubble.app.core.Settings;
import com.jubble.app.core.components.Balance;
import java.io.IOException;
import java.util.List;

public class GameStarterUtil {
  private static void recoverGameValues(GameProgress progress) {
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

  /**
   * Restore game progress if present. Otherwise start with default values.
   */
  public static void setUp() {
    try {
      GameProgress savedProgress = GameProgressSerializer.loadGame();
      recoverGameValues(savedProgress);
    } catch (IOException e) {
      System.out.println(e.getMessage());
      startFromScratch();
    }
  }
}
