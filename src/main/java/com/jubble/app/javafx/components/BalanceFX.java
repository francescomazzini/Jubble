package com.jubble.app.javafx.components;

import com.jubble.app.javafx.ThreadTaskUtil;
import com.jubble.app.javafx.tasks.BalanceTask;
import com.jubble.app.javafx.tasks.ProductionTask;
import javafx.scene.control.Label;

import java.util.Objects;

public class BalanceFX {
  private final Label balanceLabel;
  private final Label totalProductionLabel;

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
