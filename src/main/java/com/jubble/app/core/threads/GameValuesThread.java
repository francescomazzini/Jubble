package com.jubble.app.core.threads;

import com.jubble.app.core.Settings;
import com.jubble.app.core.components.Balance;
import com.jubble.app.core.components.generator.Generator;

import java.util.TimerTask;

public class GameValuesThread extends TimerTask {
  /** Increment game values */
  @Override
  public void run() {
    Balance.setPrimary(
        Balance.getPrimary()
            + Settings.getGenerators().stream()
                .mapToDouble(Generator::getProduction)
                .sum());
  }
}
