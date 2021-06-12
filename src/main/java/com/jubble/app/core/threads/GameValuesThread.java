package com.jubble.app.core.threads;

import com.jubble.app.core.components.Balance;
import com.jubble.app.core.utils.GameActions;
import java.util.TimerTask;

public class GameValuesThread extends TimerTask {
  /** Increment game values */
  @Override
  public void run() {
    Balance.addPrimary(GameActions.getTotalGeneratorsSum());
  }
}
