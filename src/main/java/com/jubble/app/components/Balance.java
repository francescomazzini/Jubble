package com.jubble.app.components;

import com.jubble.app.utils.Settings;


public class Balance {
  private Currency primary;

  public Balance() {
    this.primary = Settings.getCurrencies().get(0);
  }

  public synchronized double getPrimary() {
    return primary.getOwned();
  }

  public synchronized void setPrimary(double amount) {
    primary.setOwned(amount);
  }

}
