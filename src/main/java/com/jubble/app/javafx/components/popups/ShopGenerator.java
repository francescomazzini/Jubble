package com.jubble.app.javafx.components.popups;

import com.jubble.app.ThreadRunner;
import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.components.generator.GeneratorsSingleton;
import com.jubble.app.utils.GameActions;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ShopGenerator extends GridPane {

    private final int maxPerRow;
    private final int maxLengthPerCol;
    private final Balance bal;
    private List<List<Label>> generatorLabelsManager;
    private List<VBox> generatorVBoxManager;
    private List<Generator> generatorList;

    public ShopGenerator (int maxPerRow, List<Generator> generatorList, Balance bal, List<List<Label>> generatorLabelsManager, List<VBox> generatorVBoxManager) {
        this.maxPerRow = maxPerRow;
        this.generatorList = generatorList;
        this.maxLengthPerCol = (int) Math.ceil((double) generatorList.size() / maxPerRow);
        this.bal = bal;
        this.generatorLabelsManager = generatorLabelsManager;
        this.generatorVBoxManager = generatorVBoxManager;
    }

    public void generateShopPanel () {

        for (int i = 0; i < maxPerRow; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100 / maxPerRow);
            this.getColumnConstraints().add(column);
        }

        for (int i = 0; i < maxLengthPerCol; i++) {
            RowConstraints row = new RowConstraints(220);
            this.getRowConstraints().add(row);
        }

        for (int i = 0; i < generatorList.size(); i++) {
            Generator currentGenerator = generatorList.get(i);

            Label nameGeneratorLabel =
                    new Label(currentGenerator.getName());

            Label productionGeneratorLabel =
                    new Label(
                            "Production: "
                                    + String.format(
                                    Locale.US,
                                    "%,.2f",
                                    currentGenerator.getProductionBase())
                                    + "/s"
                    );

            Label costGeneratorLabel =
                    new Label(
                            "Cost: "
                                    + String.format(
                                    Locale.US,
                                    "%,.2f",
                                    currentGenerator.getNextCost()
                            )
                    );

            nameGeneratorLabel.getStyleClass().add("generator-title");
            productionGeneratorLabel.getStyleClass().add("generator-desc");
            costGeneratorLabel.getStyleClass().add("generator-desc");

            ImageView imageGenerator =
                    new ImageView("assets/game-components/generator" + i + ".png");
            imageGenerator.setFitHeight(58);
            imageGenerator.setFitWidth(160);


            generatorLabelsManager.add(new ArrayList<>());

            generatorLabelsManager.get(i).add(nameGeneratorLabel);
            generatorLabelsManager.get(i).add(productionGeneratorLabel);
            generatorLabelsManager.get(i).add(costGeneratorLabel);

            Button buttonBuyGenerator = new Button("Buy");
            buttonBuyGenerator.setId(i + "");
            buttonBuyGenerator.setOnAction(this::buyGenerator);
            buttonBuyGenerator.getStyleClass().add("button-buy");

            VBox topPadding = new VBox();
            topPadding.setMinHeight(25);

            VBox bottomPadding = new VBox();
            bottomPadding.setMinHeight(15);

            VBox containerGenerator = new VBox(topPadding,
                    imageGenerator,
                    nameGeneratorLabel,
                    productionGeneratorLabel,
                    costGeneratorLabel,
                    bottomPadding,
                    buttonBuyGenerator
            );

            containerGenerator.setAlignment(Pos.TOP_CENTER);
            containerGenerator.setMinHeight(100);

            this.add(containerGenerator, (i % maxPerRow), (i / maxPerRow));
        }
    }

    public void buyGenerator(ActionEvent event) {

        Button b = (Button) event.getSource();
        int id = Integer.parseInt(b.getId());

        Generator currentGenerator = GeneratorsSingleton.getGenerators().get(id);

        GameActions.buyGenerator(id, bal);

        generatorLabelsManager.get(id).get(2).setText("Cost: " + String.format(Locale.US, "%,.2f", currentGenerator.getNextCost()));
        generatorLabelsManager.get(id).get(3).setText("Qt: " + currentGenerator.getNumberOwned());

        if(currentGenerator.getNumberOwned() > 0)
            generatorVBoxManager.get(id).setVisible(true);

    }

}
