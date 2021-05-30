package com.jubble.app.javafx.pages.bodies;

import com.jubble.app.javafx.ThreadTaskUtil;
import com.jubble.app.javafx.tasks.CostNextTask;
import com.jubble.app.javafx.tasks.NumberOwnedTask;
import com.jubble.app.utils.Settings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class BodyGenerators extends VBox {

    public static final int NR_MAX_GENERATORS_PER_PAGE = 6;
    public static final int NR_MAX_GENERATORS_PER_COLUMN = 3;
    public static final int NR_MAX_GENERATORS_PER_ROW = 2;
    private final GridPane gridForGenerators;
    private final List<VBox> generatorsInfo;

    public BodyGenerators () {
        gridForGenerators = new GridPane();
        generatorsInfo = new ArrayList<>();

        this.getChildren().add(gridForGenerators);
    }

    public void addGenerator (int number, String imagePath) {

        if(generatorsInfo.size() == NR_MAX_GENERATORS_PER_PAGE)
            throw new IndexOutOfBoundsException("Max Number Generators Reached");

        Label numberOwned = new Label("Nr: " + Settings.getGenerators().get(number).getNumberOwned());
        numberOwned.getStyleClass().add("generator-desc");
        numberOwned.setVisible(false);

        ImageView v = new ImageView(imagePath);
        v.setFitHeight(70);
        v.setFitWidth(193);
        v.setVisible(false);

        NumberOwnedTask nrTask = new NumberOwnedTask(number, v, numberOwned);
        numberOwned.textProperty().bind(nrTask.messageProperty());
        ThreadTaskUtil.autoBuild(nrTask);

        VBox info = new VBox(2, v, numberOwned);
        info.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(v, new Insets(2, 0, 0, 0));

        generatorsInfo.add(info);

    }

    public void buildPage () {
        int max = NR_MAX_GENERATORS_PER_COLUMN;

        for (int i = 0; i < NR_MAX_GENERATORS_PER_ROW + 1; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100 / (NR_MAX_GENERATORS_PER_ROW + 1));
            gridForGenerators.getColumnConstraints().add(column);
        }


        for(int i = 0; i < generatorsInfo.size(); i++) {
            gridForGenerators.add(generatorsInfo.get(i), (i < max ? max - 1 : 0), (i % max));
        }

        gridForGenerators.setPrefWidth(852);
        gridForGenerators.setPrefHeight(332);
        gridForGenerators.setAlignment(Pos.CENTER);

    }

}
