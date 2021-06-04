package com.jubble.app.javafx.pages.bodies;

import com.jubble.app.components.generator.Generator;
import com.jubble.app.utils.Assets;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class BodyGenerators extends VBox {

  public static final int NR_MAX_GENERATORS_PER_PAGE = 6;
  public static final int NR_MAX_GENERATORS_PER_COLUMN = 3;
  public static final int NR_MAX_GENERATORS_PER_ROW = 2;
  private final GridPane gridForGenerators;
  private final List<VBox> generatorsInfo;
  private final List<Integer> generatorsNumbers;

  public BodyGenerators() {
    gridForGenerators = new GridPane();
    generatorsInfo = new ArrayList<>();
    generatorsNumbers = new ArrayList<>();

    this.getChildren().add(gridForGenerators);
  }

  public VBox addGenerator(int number, String imagePath) {

    Generator currentGenerator = Assets.getInstance().getGenerators().get(number);

    if (generatorsInfo.size() == NR_MAX_GENERATORS_PER_PAGE)
      throw new IndexOutOfBoundsException("Max Number Generators Reached");

    generatorsNumbers.add(number);

    Label numberOwned =
        new Label("Qt: " + currentGenerator.getNumberOwned());
    numberOwned.getStyleClass().add("generator-desc");

    ImageView v = new ImageView(imagePath);
    v.setFitHeight(70);
    v.setFitWidth(193);

    //il task serviva anche a vedere quando il generator fosse disponibile
    //NumberOwnedTask nrTask = new NumberOwnedTask(number, v, numberOwned);
    //numberOwned.textProperty().bind(nrTask.messageProperty());
    //ThreadTaskUtil.autoBuild(nrTask);

    VBox info = new VBox(2, v, numberOwned);
    info.setAlignment(Pos.TOP_CENTER);
    VBox.setMargin(v, new Insets(2, 0, 0, 0));

    generatorsInfo.add(info);

    if(currentGenerator.getNumberOwned() > 0)
      info.setVisible(true);
    else
      info.setVisible(false);

    return info;
  }

  public void buildPage() {
    int max = NR_MAX_GENERATORS_PER_COLUMN;

    for (int i = 0; i < NR_MAX_GENERATORS_PER_ROW + 1; i++) {
      ColumnConstraints column = new ColumnConstraints();
      column.setPercentWidth(100 / (NR_MAX_GENERATORS_PER_ROW + 1));
      gridForGenerators.getColumnConstraints().add(column);
    }

    for (int i = 0; i < generatorsInfo.size(); i++) {
      gridForGenerators.add(generatorsInfo.get(i), (i < max ? max - 1 : 0), (i % max));
    }

    gridForGenerators.setPrefWidth(852);
    gridForGenerators.setPrefHeight(332);
    gridForGenerators.setAlignment(Pos.CENTER);
  }

  public boolean areThereGeneratorsVisible() {
    for (int i = generatorsNumbers.get(0);
        i <= generatorsNumbers.get(generatorsNumbers.size() - 1);
        i++) if (Assets.getInstance().getGenerators().get(i).getNumberOwned() > 0) return true;

    return false;
  }
}
