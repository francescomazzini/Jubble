package com.jubble.app.javafx.components;

import com.jubble.app.core.components.generator.Generator;
import com.jubble.app.core.utils.GameActions;
import com.jubble.app.core.utils.NumberNames;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GeneratorFX {

  private final Generator generator;
  private final Label nameGeneratorLabel;
  private final Label productionGeneratorLabel;
  private final Label costGeneratorLabel;
  private final Label numberOwnedGeneratorLabel;
  private final ImageView imageGeneratorShopIcon;
  private final ImageView imageGeneratorPageIcon;
  private final Button buttonBuyGenerator;

  private VBox wrapperGeneratorAsShopElement;
  private VBox wrapperGeneratorAsPageElement;

  public GeneratorFX(Generator generator, String imageGeneratorPath) {
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

    String formattingProduction = NumberNames.createString(generator.getProductionBase());
    productionGeneratorLabel.setText("Production: " + formattingProduction + "/s");
  }

  public void setCostGeneratorLabel() {

    String formattingCost = NumberNames.createString(generator.getNextCost());
    costGeneratorLabel.setText("Cost: " + formattingCost);
  }

  public void setNumberOwnedGeneratorLabel() {
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

    wrapperGeneratorAsShopElement =
        new VBox(
            topPadding,
            imageGeneratorShopIcon,
            nameGeneratorLabel,
            productionGeneratorLabel,
            costGeneratorLabel,
            bottomPadding,
            buttonBuyGenerator);

    wrapperGeneratorAsShopElement.setAlignment(Pos.TOP_CENTER);
    wrapperGeneratorAsShopElement.setMinHeight(100);
  }

  public void setWrapperGeneratorAsPageElement() {

    wrapperGeneratorAsPageElement = new VBox(2, imageGeneratorPageIcon, numberOwnedGeneratorLabel);
    wrapperGeneratorAsPageElement.setAlignment(Pos.TOP_CENTER);

    wrapperGeneratorAsPageElement.setVisible(generator.isMoreThanZeroOwned());
  }

  public VBox getWrapperGeneratorAsShopElement() {
    return wrapperGeneratorAsShopElement;
  }

  public VBox getWrapperGeneratorAsPageElement() {
    return wrapperGeneratorAsPageElement;
  }

  public boolean isWrapperGeneratorAsPageElementVisible() {
    return wrapperGeneratorAsPageElement.isVisible();
  }

  public void buyGenerator(ActionEvent event) {

    GameActions.buyGenerator(generator);

    setCostGeneratorLabel();
    setNumberOwnedGeneratorLabel();

    if (generator.isMoreThanZeroOwned()) wrapperGeneratorAsPageElement.setVisible(true);
  }
}
