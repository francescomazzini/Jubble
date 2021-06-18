package com.jubble.app.javafx.tasks;

import com.jubble.app.core.Settings;
import com.jubble.app.core.utils.NumberNamesUtil;

/** Updates the balance label. */
public final class BalanceTask extends AbstractGameTask {

  /**
   * Called by the Task when the thread that contains this task is started every 500ms it updates
   * the progress (@updateProgress) of the currency.
   *
   * @return Void placeholder for void.
   * @throws Exception if the thread was stopped.
   */
  @Override
  protected Void call() throws Exception {
    while (true) {
      Thread.sleep(REFRESH_INTERVAL);
      updateProgress(Settings.getCurrencyList().get(0).getOwned(), 0);
    }
  }

  /**
   * When called by the task updates the progress of the balance and gives a message that is bound
   * to the label which represents the game balance.
   *
   * @param v is the actual balance variable.
   * @param v1 is not used.
   */
  @Override
  protected void updateProgress(double v, double v1) {
    updateMessage(NumberNamesUtil.createString(v));
    super.updateProgress(v, v1);
  }
}
