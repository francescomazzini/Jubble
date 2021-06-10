package com.jubble.app;

import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.components.NewSettings;
import java.util.TimerTask;

public class GameValuesThread extends TimerTask {
  /** Increment game values */
  @Override
  public void run() {
    Balance.setPrimary(
        Balance.getPrimary()
            + NewSettings.getGenerators().stream()
                .mapToDouble(Generator::getProduction)
                .sum());
  }
}
