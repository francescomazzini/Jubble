package com.jubble.app.javafx.components;

import com.jubble.app.javafx.ThreadTaskUtil;
import com.jubble.app.javafx.tasks.BalanceTask;
import com.jubble.app.javafx.tasks.ProductionTask;
import javafx.scene.control.Label;

public class BalanceFX {
  public BalanceFX(Label balanceLabel, Label totalProductionLabel) {

    BalanceTask balTask = new BalanceTask();
    balanceLabel.textProperty().bind(balTask.messageProperty());
    ThreadTaskUtil.autoBuild(balTask);

    ProductionTask prodTask = new ProductionTask();
    totalProductionLabel.textProperty().bind(prodTask.messageProperty());
    ThreadTaskUtil.autoBuild(prodTask);
  }
}
