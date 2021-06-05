package com.jubble.app;

import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.components.generator.GeneratorsSingleton;
import java.util.TimerTask;

public class GameValuesThread extends TimerTask {
  private Balance balance;

  public GameValuesThread(Balance balance) {
    this.balance = balance;
  }

  /** Increment game values */
  @Override
  public void run() {
    balance.setPrimary(
        balance.getPrimary()
            + GeneratorsSingleton.getGenerators().stream()
                .mapToDouble(Generator::getProduction)
                .sum());
  }
}
