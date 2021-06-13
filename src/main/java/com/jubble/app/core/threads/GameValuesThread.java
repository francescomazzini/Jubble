package com.jubble.app.core.threads;

import com.jubble.app.core.components.Balance;
import com.jubble.app.core.utils.GameActions;
import java.util.TimerTask;

/** Thread that increments the game value every predefined amount of time. */
public class GameValuesThread extends TimerTask {
  /** Increment balance */
  @Override
  public void run() {
    Balance.addPrimary(GameActions.getTotalGeneratorsSum());
  }
}
