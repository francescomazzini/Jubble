package com.jubble.app.javafx.components;

import com.jubble.app.core.GameActions;
import com.jubble.app.core.resources.generator.Generator;
import com.jubble.app.core.utils.NumberNamesUtil;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/** GUI representation of a generator. */
public final class GeneratorFX {
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
    this.generator = Objects.requireNonNull(generator);
    Objects.requireNonNull(imageGeneratorPath);
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

  private void setNameGeneratorLabel() {
    nameGeneratorLabel.setText(generator.getName());
  }

  private void setProductionGeneratorLabel() {
    String formattingProduction = NumberNamesUtil.createString(generator.getProductionBase());
    productionGeneratorLabel.setText("Production: " + formattingProduction + "/s");
  }

  private void setCostGeneratorLabel() {
    String formattingCost = NumberNamesUtil.createString(generator.getNextCost());
    costGeneratorLabel.setText("Cost: " + formattingCost);
  }

  private void setNumberOwnedGeneratorLabel() {
    numberOwnedGeneratorLabel.setText("Qt: " + generator.getNumberOwned());
  }

  private void setImageGeneratorShopIcon(String imageGeneratorPath) {
    final int HEIGHT = 58;
    final int WIDTH = 160;
    imageGeneratorShopIcon.setImage(new Image(imageGeneratorPath));
    imageGeneratorShopIcon.setFitHeight(HEIGHT);
    imageGeneratorShopIcon.setFitWidth(WIDTH);
  }

  private void setImageGeneratorPageIcon(String imageGeneratorPath) {
    final int WIDTH = 70;
    final int HEIGHT = 193;
    imageGeneratorPageIcon.setImage(new Image(imageGeneratorPath));
    imageGeneratorPageIcon.setFitHeight(WIDTH);
    imageGeneratorPageIcon.setFitWidth(HEIGHT);
  }

  private void setWrapperGeneratorAsShopElement() {
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

  private void setWrapperGeneratorAsPageElement() {
    wrapperGeneratorAsPageElement = new VBox(2, imageGeneratorPageIcon, numberOwnedGeneratorLabel);
    wrapperGeneratorAsPageElement.setAlignment(Pos.TOP_CENTER);
    wrapperGeneratorAsPageElement.setVisible(generator.isMoreThanZeroOwned());
  }

  /**
   * Returns the VBox of a generator.
   *
   * @return the shop version of a generator.
   */
  public VBox getWrapperGeneratorAsShopElement() {
    return wrapperGeneratorAsShopElement;
  }

  /**
   * Returns the VBox of a generator.
   *
   * @return the page version of a generator.
   */
  public VBox getWrapperGeneratorAsPageElement() {
    return wrapperGeneratorAsPageElement;
  }

  /**
   * Says if the generator element is visible.
   *
   * @return true if isVisible, false otherwise.
   */
  public boolean isWrapperGeneratorAsPageElementVisible() {
    return wrapperGeneratorAsPageElement.isVisible();
  }

  private void buyGenerator(ActionEvent event) {
    GameActions.buyGenerator(generator);
    setCostGeneratorLabel();
    setNumberOwnedGeneratorLabel();

    if (generator.isMoreThanZeroOwned()) wrapperGeneratorAsPageElement.setVisible(true);
  }
}
