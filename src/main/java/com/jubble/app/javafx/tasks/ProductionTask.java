package com.jubble.app.javafx.tasks;

import com.jubble.app.core.GameActions;
import com.jubble.app.core.utils.NumberNamesUtil;

public class ProductionTask extends AbstractTask {

  @Override
  protected Void call() throws Exception {
    while (true) {

      Thread.sleep(500);
      updateProgress(GameActions.getTotalGeneratorsSum(), 0);
    }
  }

  @Override
  protected void updateProgress(double v, double v1) {
    updateMessage(NumberNamesUtil.createString(v) + "/s ");
    super.updateProgress(v, v1);
  }
}
