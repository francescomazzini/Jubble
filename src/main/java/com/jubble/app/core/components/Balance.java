package com.jubble.app.core.components;

import com.jubble.app.core.Settings;

/**
 * Bill Pugh Singleton pattern Implementation. This class contains all the currencies used in this
 * game. The use of the singleton pattern ensures an unique instance of this class across the app.
 */
public class Balance {
  /** Primary currency of the game. */
  private Currency primary;

  private Balance() {
    this.primary = Settings.getCurrencies().get(0);
  }

  private static class BalanceCage {
    private static final Balance INSTANCE = new Balance();
  }

  /**
   * Get amount of primary currency owned by the player.
   *
   * @return amount of primary currency owned
   */
  public static synchronized double getPrimary() {
    return BalanceCage.INSTANCE.primary.getOwned();
  }

  /**
   * Add amount of primary currency owned by the player.
   *
   * @param amount is the amount of primary currency owned by the player
   */
  public static synchronized void addPrimary(double amount) {
    BalanceCage.INSTANCE.primary.setOwned(getPrimary() + amount);
  }

  /**
   * This method is NOT Thread Safe because it is meant to be used only at the beginning to restore
   * values or only for testing
   *
   * @param amount is the amount of primary currency owned by the player
   */
  public static void setPrimary(double amount) {
    BalanceCage.INSTANCE.primary.setOwned(amount);
  }
}
