package com.jubble.app.javafx.components.bodiesMainPage;

import java.util.List;

import com.jubble.app.javafx.components.GeneratorFX;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class BodyGenerators extends VBox {
  public static final int MAX_GENERATORS_PER_PAGE = 6;
  public static final int MAX_GENERATORS_PER_COLUMN = 3;
  public static final int MAX_GENERATORS_PER_ROW = 2;

  private final GridPane gridForGenerators;
  private final List<GeneratorFX> generatorFXList;
  private final int beginIndex;
  private final int endIndex;

  public BodyGenerators(List<GeneratorFX> generatorList, int beginIndex) {
    gridForGenerators = new GridPane();
    this.generatorFXList = generatorList;
    this.beginIndex = beginIndex;

    endIndex = Math.min((beginIndex + MAX_GENERATORS_PER_PAGE), generatorList.size());

    buildPage();

    this.getChildren().add(gridForGenerators);
  }

  public void buildPage() {
    final double HUNDRED = 100.0;

    for (int i = 0; i < MAX_GENERATORS_PER_ROW + 1; i++) {
      ColumnConstraints column = new ColumnConstraints();
      column.setPercentWidth(HUNDRED / (MAX_GENERATORS_PER_ROW + 1));
      gridForGenerators.getColumnConstraints().add(column);
    }

    for (int i = beginIndex; i < endIndex; i++) {
      gridForGenerators.add(generatorFXList.get(i).getWrapperGeneratorAsPageElement(),
              ((i % MAX_GENERATORS_PER_PAGE) < MAX_GENERATORS_PER_COLUMN ? MAX_GENERATORS_PER_COLUMN - 1 : 0),
              (i % MAX_GENERATORS_PER_COLUMN));
    }

    final int WIDTH = 852;
    final int HEIGHT = 332;
    gridForGenerators.setPrefWidth(WIDTH);
    gridForGenerators.setPrefHeight(HEIGHT);
    gridForGenerators.setAlignment(Pos.CENTER);
  }

  public boolean areThereGeneratorsVisible() {
    for (int i = beginIndex; i < endIndex; i++)
      if (generatorFXList.get(i).isWrapperGeneratorAsPageElementVisible())
        return true;

    return false;
  }
}
