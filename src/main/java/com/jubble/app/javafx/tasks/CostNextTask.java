package com.jubble.app.javafx.tasks;

import com.jubble.app.utils.Settings;
import java.util.Locale;

public class CostNextTask extends AbstractTask {

  /** This int generator represents which generator of the array this task refers to. */
  private int generator;

  public CostNextTask(int i) {
    this.generator = i;
  }

  /**
   * This method is called when the thread that contains this task is started every 500ms it updates
   * the progress (@updateProgress) of the generator cost
   *
   * @return nothing
   * @throws Exception if the thread was stopped
   */
  @Override
  protected Void call() throws Exception {
    while (true) {

      Thread.sleep(500);
      updateProgress(Settings.getGenerators().get(generator).getNextCost(), 0);
    }
  }

  /**
   * This method is called by @call every 500ms and it updates the progress of the generator cost.
   * Not only does it update the progress itself but also it gives a message that is binded to the
   * label which represents the next cost generator
   *
   * @param v is the actual generator next cost variable
   * @param v1 is not used but the ovverrided method provided two inputs
   */
  @Override
  protected void updateProgress(double v, double v1) {
    updateMessage("Cost: " + String.format(Locale.US, "%,.2f", v));
    super.updateProgress(v, v1);
  }
}
