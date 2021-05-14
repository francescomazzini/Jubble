package com.jubble.app.components;

import com.jubble.app.utils.Settings;

/**
 *
 * This class contains all the currencies used in this game.
 * Primary is the primary currency and it uses synchronized methods
 * because it is often accessed by more than one thread
 *
 * */

public class Balance {
  private Currency primary;

  public Balance() {
    this.primary = Settings.getCurrencies().get(0);
  }


  /**
   * Get amount of primary currency owned by the player
   *
   * @return amount of primary currency owned
   */

  public synchronized double getPrimary() {
    return primary.getOwned();
  }

  /**
   * Set amount of primary currency owned by the player
   *
   * @param amount is the amount of primary currency owned by the player
   */

  public synchronized void setPrimary(double amount) {
    primary.setOwned(amount);
  }

}
