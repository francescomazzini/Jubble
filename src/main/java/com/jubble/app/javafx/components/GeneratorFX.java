package com.jubble.app.javafx.components;

import com.jubble.app.components.generator.Generator;
import com.jubble.app.utils.GameActions;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Locale;

public class GeneratorFX {

    private Generator generator;
    private Label nameGeneratorLabel;
    private Label productionGeneratorLabel;
    private Label costGeneratorLabel;
    private Label numberOwnedGeneratorLabel;
    private ImageView imageGeneratorShopIcon;
    private ImageView imageGeneratorPageIcon;
    private Button buttonBuyGenerator;

    private VBox wrapperGeneratorAsShopElement;
    private VBox wrapperGeneratorAsPageElement;

    public GeneratorFX (Generator generator, String imageGeneratorPath) {
        this.generator = generator;

        nameGeneratorLabel = new Label();
        productionGeneratorLabel = new Label();
        costGeneratorLabel = new Label();
        numberOwnedGeneratorLabel = new Label();
        imageGeneratorShopIcon = new ImageView();
        imageGeneratorPageIcon = new ImageView();
        buttonBuyGenerator = new Button("Buy");

        setNameGeneratorLabel();
        setProductionGeneratorLabel();
        setCostGeneratorLabel();
        setNumberOwnedGeneratorLabel();
        setImageGeneratorShopIcon(imageGeneratorPath);
        setImageGeneratorPageIcon(imageGeneratorPath);
        setWrapperGeneratorAsShopElement();
        setWrapperGeneratorAsPageElement();

        buttonBuyGenerator.setOnAction(this::buyGenerator);

        nameGeneratorLabel.getStyleClass().add("generator-title");
        productionGeneratorLabel.getStyleClass().add("generator-desc");
        costGeneratorLabel.getStyleClass().add("generator-desc");
        numberOwnedGeneratorLabel.getStyleClass().add("generator-desc");
        buttonBuyGenerator.getStyleClass().add("button-buy");
    }

    public void setNameGeneratorLabel() {
        nameGeneratorLabel.setText(generator.getName());
    }

    public void setProductionGeneratorLabel() {

        String formattingProduction = String.format(Locale.US,"%,.2f",generator.getProductionBase());
        productionGeneratorLabel.setText("Production: " + formattingProduction + "/s");

    }

    public void setCostGeneratorLabel() {

        String formattingCost = String.format(Locale.US,"%,.2f",generator.getNextCost());
        costGeneratorLabel.setText("Cost: " + formattingCost);

    }

    public void setNumberOwnedGeneratorLabel () {
        numberOwnedGeneratorLabel.setText("Qt: " + generator.getNumberOwned());
    }

    public void setImageGeneratorShopIcon(String imageGeneratorPath) {
        imageGeneratorShopIcon.setImage(new Image(imageGeneratorPath));
        imageGeneratorShopIcon.setFitHeight(58);
        imageGeneratorShopIcon.setFitWidth(160);
    }

    public void setImageGeneratorPageIcon(String imageGeneratorPath) {
        imageGeneratorPageIcon.setImage(new Image(imageGeneratorPath));
        imageGeneratorPageIcon.setFitHeight(70);
        imageGeneratorPageIcon.setFitWidth(193);
    }

    public void setWrapperGeneratorAsShopElement() {
        VBox topPadding = new VBox();
        topPadding.setMinHeight(25);

        VBox bottomPadding = new VBox();
        bottomPadding.setMinHeight(15);

        wrapperGeneratorAsShopElement = new VBox(
                topPadding,
                imageGeneratorShopIcon,
                nameGeneratorLabel,
                productionGeneratorLabel,
                costGeneratorLabel,
                bottomPadding,
                buttonBuyGenerator
        );

        wrapperGeneratorAsShopElement.setAlignment(Pos.TOP_CENTER);
        wrapperGeneratorAsShopElement.setMinHeight(100);
    }

    public void setWrapperGeneratorAsPageElement() {

        wrapperGeneratorAsPageElement = new VBox(2, imageGeneratorPageIcon, numberOwnedGeneratorLabel);
        wrapperGeneratorAsPageElement.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(imageGeneratorPageIcon, new Insets(2, 0, 0, 0));

        if(generator.getNumberOwned() > 0)
            wrapperGeneratorAsPageElement.setVisible(true);
        else
            wrapperGeneratorAsPageElement.setVisible(false);

    }

    public VBox getWrapperGeneratorAsShopElement() {
        return wrapperGeneratorAsShopElement;
    }

    public VBox getWrapperGeneratorAsPageElement() {
        return wrapperGeneratorAsPageElement;
    }

    public boolean isWrapperGeneratorAsPageElementVisible () {
        return wrapperGeneratorAsPageElement.isVisible();
    }

    public void buyGenerator(ActionEvent event) {

        GameActions.buyGenerator(generator);

        setCostGeneratorLabel();
        setNumberOwnedGeneratorLabel();

        if(generator.getNumberOwned() > 0)
            wrapperGeneratorAsPageElement.setVisible(true);

    }
}
