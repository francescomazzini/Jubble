package com.jubble.app.javafx;

import com.jubble.app.core.GameActions;
import com.jubble.app.core.Settings;
import com.jubble.app.core.resources.generator.Generator;
import com.jubble.app.javafx.components.BalanceFX;
import com.jubble.app.javafx.components.GeneratorFX;
import com.jubble.app.javafx.components.bodiesMainPage.BodyGeneratorPos;
import com.jubble.app.javafx.components.bodiesMainPage.BodyGenerators;
import com.jubble.app.javafx.components.popups.ShopGenerator;
import com.jubble.app.javafx.components.popups.ShopPos;
import java.net.URL;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

/** Main application panel. Contains all the submodules for the various panels of the game. */
public class ControllerFX implements Initializable {
  /**
   * Maps the name of the page with its physical element. A mainScreenBodyPages is a body page of
   * the Main Screen, which is the part in which the background with the generators is placed.
   */
  private Map<String, VBox> mainScreenBodyPages;
  /** Contains all the Javafx elements of a generator. */
  private List<GeneratorFX> generatorFXList;

  private int currentSelectedPage = 0;

  @FXML private AnchorPane shopPopUp;
  @FXML private VBox mainBody;
  @FXML private Label balanceLabel;
  @FXML private VBox shopPanel;
  @FXML private Label totalProductionLabel;
  @FXML private VBox shopPanelContainer;

  private void setShopVisibility(boolean status) {
    shopPanelContainer.setVisible(status);
    shopPanel.setVisible(status);
  }

  @FXML
  public void showShopPanel() {
    setShopVisibility(true);
  }

  @FXML
  public void hideShopPanel() {
    setShopVisibility(false);
  }

  private void switchMainPage(int newPageNumber) {
    String namePageWanted = "page" + newPageNumber;

    BodyGenerators bodyWanted = (BodyGenerators) mainScreenBodyPages.get(namePageWanted);

    if (bodyWanted != null)
      if (bodyWanted.areThereGeneratorsVisible()) {
        mainBody.getChildren().clear();
        mainBody.getChildren().add(bodyWanted);
        this.currentSelectedPage = newPageNumber;
      }
  }

  @FXML
  public void switchMainPageLeft() {
    switchMainPage(currentSelectedPage + 1);
  }

  @FXML
  public void switchMainPageRight() {
    switchMainPage(currentSelectedPage - 1);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    setUpBalanceFX();
    setUpGeneratorFX();
    generateShopPane();
    generateGeneratorPage();
  }

  /**
   * Sets up all the graphic elements for all {@link
   * com.jubble.app.core.resources.generator.Generator}, creating a {@link
   * com.jubble.app.javafx.components.GeneratorFX} for each Generator.
   */
  public void setUpGeneratorFX() {
    generatorFXList = new ArrayList<>();
    List<Generator> generatorList = Settings.getGeneratorList();

    for (int i = 0; i < generatorList.size(); i++) {
      Generator currentGenerator = generatorList.get(i);

      generatorFXList.add(
          new GeneratorFX(currentGenerator, "assets/game-components/generator" + i + ".png"));
    }
  }

  /**
   * Set up the graphical representation of the game Balance {@link
   * com.jubble.app.core.resources.Balance}.
   */
  public void setUpBalanceFX() {
    new BalanceFX(balanceLabel, totalProductionLabel);
  }

  /**
   * Generates the Shop pane instantiating {@link
   * com.jubble.app.javafx.components.popups.ShopGenerator} and adding it to the shopPopUp. The
   * popup size must be set according to the number of Generators that were defined. The shop
   * visibility is hidden by default and toggled by player action.
   */
  public void generateShopPane() {
    ShopGenerator shop = new ShopGenerator(generatorFXList);
    shop.generateShopPanel();
    shopPopUp.getChildren().add(shop);

    int nGenerators = GameActions.getNumberOfGenerators();
    shopPopUp.setMinHeight(
        220 * Math.ceil((double) nGenerators / ShopPos.ROW_GENERATOR_MAX.value()));

    hideShopPanel();
  }

  /**
   * Generates the page in the background which contains the generators owned by the player. The
   * number of generator per page is define in the enum {@link
   * com.jubble.app.javafx.components.bodiesMainPage.BodyGeneratorPos}
   */
  public void generateGeneratorPage() {
    mainScreenBodyPages = new HashMap<>();
    int counter = 0;

    for (int i = 0; i < generatorFXList.size(); i++) {
      if (i % BodyGeneratorPos.PAGE_MAX.value() == 0) {
        mainScreenBodyPages.put("page" + counter, new BodyGenerators(generatorFXList, i));
        counter++;
      }
    }
    mainBody.getChildren().clear();
    mainBody.getChildren().add(mainScreenBodyPages.get("page0"));
  }
}
