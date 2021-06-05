package com.jubble.app.javafx.components.bodiesMainPage;

import java.util.List;

import com.jubble.app.javafx.components.GeneratorFX;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class BodyGenerators extends VBox {

  public static final int NR_MAX_GENERATORS_PER_PAGE = 6;
  public static final int NR_MAX_GENERATORS_PER_COLUMN = 3;
  public static final int NR_MAX_GENERATORS_PER_ROW = 2;

  private final GridPane gridForGenerators;
  private List<GeneratorFX> generatorFXList;
  private int beginIndex;
  private int endIndex;

  public BodyGenerators(List<GeneratorFX> generatorList, int beginIndex) {
    gridForGenerators = new GridPane();
    this.generatorFXList = generatorList;
    this.beginIndex = beginIndex;

    if((beginIndex + NR_MAX_GENERATORS_PER_PAGE) > generatorList.size())
      endIndex = generatorList.size();
    else
      endIndex = beginIndex + NR_MAX_GENERATORS_PER_PAGE;

    buildPage();

    this.getChildren().add(gridForGenerators);
  }

  public void buildPage() {
    int maxXCol = NR_MAX_GENERATORS_PER_COLUMN;
    int maxXRow = NR_MAX_GENERATORS_PER_ROW;

    for (int i = 0; i < maxXRow + 1; i++) {
      ColumnConstraints column = new ColumnConstraints();
      column.setPercentWidth(100 / (maxXRow + 1));
      gridForGenerators.getColumnConstraints().add(column);
    }

    for (int i = beginIndex; i < endIndex; i++) {
      gridForGenerators.add(generatorFXList.get(i).getWrapperGeneratorAsPageElement(),
              (i < maxXCol ? maxXCol - 1 : 0),
              (i % maxXCol));
    }

    gridForGenerators.setPrefWidth(852);
    gridForGenerators.setPrefHeight(332);
    gridForGenerators.setAlignment(Pos.CENTER);
  }

  public boolean areThereGeneratorsVisible() {
    for (int i = beginIndex; i < endIndex; i++)
      if (generatorFXList.get(i).isWrapperGeneratorAsPageElementVisible())
        return true;

    return false;
  }
}
