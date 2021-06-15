package com.jubble.app.core.resources;

import com.jubble.app.core.Settings;

/**
 * This class contains all the currencies used in this
 * game.
 * The use of the singleton pattern ensures an unique instance of this class across the app.
 * Bill Pugh Singleton pattern Implementation.
 */
public class Balance {
  /** Primary currency of the game. */
  private final Currency primary;
  private static final int DEFAULT_PRIMARY_CURRENCY = 0;

  private Balance() {
    this.primary = Settings.getCurrencyList().get(DEFAULT_PRIMARY_CURRENCY);
  }

  private static class BalanceCage {
    private static final Balance INSTANCE = new Balance();
  }

  /**
   * Get amount of primary currency owned by the player.
   *
   * @return amount of primary currency owned by the player.
   */
  public static synchronized double getPrimary() {
    return BalanceCage.INSTANCE.primary.getOwned();
  }

  /**
   * Add amount of primary currency owned by the player.
   *
   * @param amount is the amount of primary currency owned by the player.
   */
  public static synchronized void addPrimary(double amount) {
    BalanceCage.INSTANCE.primary.setOwned(getPrimary() + amount);
  }

  /**
   * This method is NOT Thread Safe because it is meant to be used only at the beginning to restore
   * values or only for testing
   *
   * @param amount is the amount of primary currency to be set into balance.
   */
  public static void setPrimary(double amount) {
    BalanceCage.INSTANCE.primary.setOwned(amount);
  }
}
