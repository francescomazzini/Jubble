package com.jubble.app;

import com.jubble.app.components.Balance;
import com.jubble.app.components.Generator;
import com.jubble.app.utils.Settings;
import java.util.TimerTask;

public class IncrementValues extends TimerTask {
  private Balance balance;

  public IncrementValues(Balance balance) {
    this.balance = balance;
  }

  /** Increment game values */
  @Override
  public void run() {
    balance.setPrimary(
        balance.getPrimary()
            + Settings.getGenerators().stream().mapToDouble(Generator::getProduction).sum());
  }
}
