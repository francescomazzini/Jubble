package com.jubble.app.javafx.tasks;

import com.jubble.app.core.Settings;
import com.jubble.app.core.utils.NumberNames;

public class BalanceTask extends AbstractTask {

  /**
   * This method is called when the thread that contains this task is started every 500ms it updates
   * the progress (@updateProgress) of the currency
   *
   * @return nothing
   * @throws Exception if the thread was stopped
   */
  @Override
  protected Void call() throws Exception {
    while (true) {

      Thread.sleep(500);
      updateProgress(Settings.getCurrencies().get(0).getOwned(), 0);
    }
  }

  /**
   * This method is called by @call every 500ms and it updates the progress of the balance. Not only
   * does it update the progress itself but also it gives a message that is binded to the label
   * which represents the game balance
   *
   * @param v is the actual balance variable
   * @param v1 is not used but the ovverrided method provided two inputs
   */
  @Override
  protected void updateProgress(double v, double v1) {
    updateMessage(NumberNames.createString(v));
    super.updateProgress(v, v1);
  }
}
