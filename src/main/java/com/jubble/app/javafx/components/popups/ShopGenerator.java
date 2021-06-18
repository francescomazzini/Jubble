package com.jubble.app.javafx.components.popups;

import com.jubble.app.javafx.components.GeneratorFX;
import java.util.List;
import java.util.Objects;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * Defines the shop page where the user can purchase the generators.
 */
public final class ShopGenerator extends GridPane {
  private final int maxLengthPerCol;
  private final List<GeneratorFX> generatorList;

  /**
   * Generates the shop panel.
   * @param generatorList list of generators to be inserted in the window.
   */
  public ShopGenerator(List<GeneratorFX> generatorList) {
    this.generatorList = Objects.requireNonNull(generatorList);
    this.maxLengthPerCol =
        (int) Math.ceil((double) generatorList.size() / ShopPos.ROW_GENERATOR_MAX.value());
  }

  /**
   * Fills the shop window.
   */
  public void generateShopPanel() {
    final double HUNDRED = 100;

    for (int i = 0; i < ShopPos.ROW_GENERATOR_MAX.value(); i++) {
      ColumnConstraints column = new ColumnConstraints();
      column.setPercentWidth(HUNDRED / ShopPos.ROW_GENERATOR_MAX.value());
      this.getColumnConstraints().add(column);
    }

    for (int i = 0; i < maxLengthPerCol; i++) {
      RowConstraints row = new RowConstraints(220);
      this.getRowConstraints().add(row);
    }

    for (int i = 0; i < generatorList.size(); i++) {
      GeneratorFX currentGeneratorFX = generatorList.get(i);

      this.add(
          currentGeneratorFX.getWrapperGeneratorAsShopElement(),
          (i % ShopPos.ROW_GENERATOR_MAX.value()),
          (i / ShopPos.ROW_GENERATOR_MAX.value()));
    }
  }
}
