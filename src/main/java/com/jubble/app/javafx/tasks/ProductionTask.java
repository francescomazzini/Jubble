package com.jubble.app.javafx.tasks;

import com.jubble.app.components.generator.Generator;
import com.jubble.app.utils.NumberNames;
import com.jubble.app.components.GeneratorsSingleton;

public class ProductionTask extends AbstractTask {

  @Override
  protected Void call() throws Exception {
    while (true) {

      Thread.sleep(500);
      updateProgress(
          GeneratorsSingleton.getGenerators().stream().mapToDouble(Generator::getProduction).sum(),
          0);
    }
  }

  @Override
  protected void updateProgress(double v, double v1) {
    updateMessage(NumberNames.createString(v) + "/s ");
    super.updateProgress(v, v1);
  }
}
