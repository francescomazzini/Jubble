package com.jubble.app.core.threads;

import com.jubble.app.core.GameActions;
import com.jubble.app.core.resources.Balance;
import java.util.TimerTask;

/** Thread that increments the game value every predefined amount of time. */
public class GameValuesThread extends TimerTask {
  /** Increment balance at each run. */
  @Override
  public void run() {
    Balance.addPrimary(GameActions.getTotalGeneratorsSum());
  }
}
