package com.jubble.app.javafx.tasks;

import com.jubble.app.core.Settings;
import com.jubble.app.core.components.generator.Generator;
import com.jubble.app.core.utils.NumberNames;

public class ProductionTask extends AbstractTask {

  @Override
  protected Void call() throws Exception {
    while (true) {

      Thread.sleep(500);
      updateProgress(
          Settings.getGenerators().stream().mapToDouble(Generator::getProduction).sum(), 0);
    }
  }

  @Override
  protected void updateProgress(double v, double v1) {
    updateMessage(NumberNames.createString(v) + "/s ");
    super.updateProgress(v, v1);
  }
}
