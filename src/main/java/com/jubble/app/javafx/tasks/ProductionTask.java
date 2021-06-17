package com.jubble.app.javafx.tasks;

import com.jubble.app.core.GameActions;
import com.jubble.app.core.utils.NumberNamesUtil;

public final class ProductionTask extends AbstractGameTask {

  @Override
  protected Void call() throws Exception {
    while (true) {

      Thread.sleep(REFRESH_INTERVAL);
      updateProgress(GameActions.getSumTotalProductionGenerators(), 0);
    }
  }

  @Override
  protected void updateProgress(double v, double v1) {
    updateMessage(NumberNamesUtil.createString(v) + "/s ");
    super.updateProgress(v, v1);
  }
}
