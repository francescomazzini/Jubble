package com.jubble.app.javafx.components.bodiesMainPage;

import com.jubble.app.javafx.components.GeneratorFX;
import java.util.List;
import java.util.Objects;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/** Creates body elements of the generators in the game. */
public final class BodyGenerators extends VBox {
  private final GridPane gridForGenerators;
  private final List<GeneratorFX> generatorFXList;
  private final int beginIndex;
  private final int endIndex;

  /**
   * Create box of generators in the body.
   *
   * @param generatorList list of generators.
   * @param beginIndex begin of the generators in the body.
   */
  public BodyGenerators(List<GeneratorFX> generatorList, int beginIndex) {
    gridForGenerators = new GridPane();
    this.generatorFXList = Objects.requireNonNull(generatorList);
    this.beginIndex = beginIndex;

    endIndex = Math.min((beginIndex + BodyGeneratorPos.PAGE_MAX.value()), generatorList.size());
    buildPage();

    this.getChildren().add(gridForGenerators);
  }

  /** Builds the body page. */
  public void buildPage() {
    final double hundred = 100.0;

    for (int i = 0; i <= BodyGeneratorPos.ROW_MAX.value(); i++) {
      ColumnConstraints column = new ColumnConstraints();
      column.setPercentWidth(hundred / (BodyGeneratorPos.ROW_MAX.value() + 1));
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

    final int width = 852;
    final int height = 302;
    gridForGenerators.setPrefWidth(width);
    gridForGenerators.setPrefHeight(height);
    gridForGenerators.setAlignment(Pos.CENTER);
  }

  /**
   * Says if there are generators visible in the body page.
   *
   * @return true if generators are visible, false otherwise.
   */
  public boolean areThereGeneratorsVisible() {
    for (int i = beginIndex; i < endIndex; i++) {
      if (generatorFXList.get(i).isWrapperGeneratorAsPageElementVisible()) {
        return true;
      }
    }
    return false;
  }
}
