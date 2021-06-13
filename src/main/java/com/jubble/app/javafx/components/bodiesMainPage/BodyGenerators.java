package com.jubble.app.javafx.components.bodiesMainPage;

import com.jubble.app.javafx.components.GeneratorFX;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class BodyGenerators extends VBox {
  private final GridPane gridForGenerators;
  private final List<GeneratorFX> generatorFXList;
  private final int beginIndex;
  private final int endIndex;

  public BodyGenerators(List<GeneratorFX> generatorList, int beginIndex) {
    gridForGenerators = new GridPane();
    this.generatorFXList = generatorList;
    this.beginIndex = beginIndex;

    endIndex = Math.min((beginIndex + BodyGeneratorPos.PAGE_MAX.value()), generatorList.size());

    buildPage();

    this.getChildren().add(gridForGenerators);
  }

  public void buildPage() {
    final double HUNDRED = 100.0;

    for (int i = 0; i <= BodyGeneratorPos.ROW_MAX.value(); i++) {
      ColumnConstraints column = new ColumnConstraints();
      column.setPercentWidth(HUNDRED / (BodyGeneratorPos.ROW_MAX.value() + 1));
      gridForGenerators.getColumnConstraints().add(column);
    }

    for (int i = beginIndex; i < endIndex; i++) {
      gridForGenerators.add(
          generatorFXList.get(i).getWrapperGeneratorAsPageElement(),
          ((i % BodyGeneratorPos.PAGE_MAX.value()) < BodyGeneratorPos.COLUMN_MAX.value()
              ? BodyGeneratorPos.COLUMN_MAX.value() - 1
              : 0),
          (i % BodyGeneratorPos.COLUMN_MAX.value()));
    }

    final int WIDTH = 852;
    final int HEIGHT = 302;
    gridForGenerators.setPrefWidth(WIDTH);
    gridForGenerators.setPrefHeight(HEIGHT);
    gridForGenerators.setAlignment(Pos.CENTER);
  }

  public boolean areThereGeneratorsVisible() {
    for (int i = beginIndex; i < endIndex; i++)
      if (generatorFXList.get(i).isWrapperGeneratorAsPageElementVisible()) return true;
    return false;
  }
}
