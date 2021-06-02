package com.jubble.app.javafx.tasks;

import com.jubble.app.components.generator.Generator;
import com.jubble.app.utils.Assets;
import java.util.Locale;

public class ProductionTask extends AbstractTask {

  @Override
  protected Void call() throws Exception {
    while (true) {

      Thread.sleep(500);
      updateProgress(
          Assets.getInstance().getGenerators().stream().mapToDouble(Generator::getProduction).sum(),
          0);
    }
  }

  @Override
  protected void updateProgress(double v, double v1) {
    updateMessage(String.format(Locale.US, "%,.2f", v) + "/s ");
    super.updateProgress(v, v1);
  }
}
