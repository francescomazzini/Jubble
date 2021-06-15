package com.jubble.app.javafx.components;

import com.jubble.app.javafx.ThreadTaskUtil;
import com.jubble.app.javafx.tasks.BalanceTask;
import com.jubble.app.javafx.tasks.ProductionTask;
import java.util.Objects;
import javafx.scene.control.Label;

/** Defines the GUI representation of the Balance class. */
public class BalanceFX {
  private final Label balanceLabel;
  private final Label totalProductionLabel;

  /**
   * Create a GUI balance element.
   *
   * @param balanceLabel label of the balance.
   * @param totalProductionLabel label of total production.
   */
  public BalanceFX(Label balanceLabel, Label totalProductionLabel) {
    this.balanceLabel = Objects.requireNonNull(balanceLabel);
    this.totalProductionLabel = Objects.requireNonNull(totalProductionLabel);
    buildBalanceTask();
    buildProductionTask();
  }

  private void buildBalanceTask() {
    BalanceTask balTask = new BalanceTask();
    balanceLabel.textProperty().bind(balTask.messageProperty());
    ThreadTaskUtil.autoBuild(balTask);
  }

  private void buildProductionTask() {
    ProductionTask prodTask = new ProductionTask();
    totalProductionLabel.textProperty().bind(prodTask.messageProperty());
    ThreadTaskUtil.autoBuild(prodTask);
  }
}
