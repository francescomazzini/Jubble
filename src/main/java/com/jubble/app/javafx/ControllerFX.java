package com.jubble.app.javafx;

import com.jubble.app.components.generator.Generator;
import com.jubble.app.components.generator.GeneratorsSingleton;
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

public class ControllerFX implements Initializable {

  /**
   * bodyPages is a Map which contains all the body pages of the game. A body page is a body of the
   * Main Screen, that is the part in the background with the generators placed. Since there can be
   * n generators, bodyPages keeps bodies, each has 6 generators, and associate to them a string
   * (ex: "page0", "page13", ...)
   */
  private Map<String, VBox> bodyPages;

  /**
   * generatorFXList is a list which group all the {@link
   * com.jubble.app.javafx.components.GeneratorFX} of the game
   */
  private static List<GeneratorFX> generatorFXList;

  private int currentSelectedPage = 0;

  /**
   * Each of the following variables refers to an existing javafx object which is contained in FXML
   * file and it has an ID equals to the name of these variables
   */
  @FXML private AnchorPane shopAnchorPane;

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

  public void switchMainPage(int newPageNumber) {
    String namePageWanted = "page" + newPageNumber;

    BodyGenerators bodyWanted = (BodyGenerators) bodyPages.get(namePageWanted);

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

  /**
   * Replaces the constructor of the controller which cannot have a constructor. It is meant to run
   * before the GUI is shown to the user.
   *
   * <p>What is done in this method:
   *
   * <ul>
   *   <li>calls {@link #setUpBalanceFX()} which set up the Balance FX elements
   *   <li>calls {@link #setUpGeneratorFX()} which set up the Generator FX elements for each
   *       Generator FX
   *   <li>calls {@link #generateShopPane()} which generate the graphic of the shop panel
   *   <li>calls {@link #generateGeneratorPage()} which generate the graphic of the place of
   *       Generator FX in the background
   * </ul>
   *
   * @param url not used
   * @param resourceBundle not used
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    setUpBalanceFX();
    setUpGeneratorFX();
    generateShopPane();
    generateGeneratorPage();
  }

  /**
   * This method set up all the graphic elements for all {@link
   * com.jubble.app.components.generator.Generator}, creating a {@link
   * com.jubble.app.javafx.components.GeneratorFX} for each of them, which includes all the graphic
   * elements needed for the graphical representation of a Generator
   */
  public void setUpGeneratorFX() {

    generatorFXList = new ArrayList<>();

    List<Generator> generatorList = GeneratorsSingleton.getGenerators();

    for (int i = 0; i < generatorList.size(); i++) {
      Generator currentGenerator = generatorList.get(i);

      generatorFXList.add(
          new GeneratorFX(currentGenerator, "assets/game-components/generator" + i + ".png"));
    }
  }

  /** Set up the graphical representation of the {@link com.jubble.app.components.Balance} class */
  public void setUpBalanceFX() {
    new BalanceFX(balanceLabel, totalProductionLabel);
  }

  /**
   * Generates the Shop pane instantiating {@link
   * com.jubble.app.javafx.components.popups.ShopGenerator} and adding it to the Anchor Pane which
   * is the actual popup. Thus the popup size must be set according to the number of Generators
   * which must be inside it. The the shop is hidden by default, the visibility is toggled by player
   * action.
   */
  public void generateShopPane() {

    ShopGenerator shop = new ShopGenerator(generatorFXList);

    shop.generateShopPanel();

    shopAnchorPane.getChildren().add(shop);

    int nGenerators = GeneratorsSingleton.getGenerators().size();
    shopAnchorPane.setMinHeight(
        220 * Math.ceil((double) nGenerators / ShopPos.ROW_GENERATOR_MAX.value()));

    hideShopPanel();
  }

  /**
   * This method generates the page in the background which contains the generators bought with
   * their quantity. Therefore a number (NR_MAX_GENERATORS_PER_PAGE) of generators must be in a
   * page. This page created using {@link
   * com.jubble.app.javafx.components.bodiesMainPage.BodyGenerators} is put in the HashMap which
   * contains all the pages and associate them a String, like "page0", "page9",...
   */
  public void generateGeneratorPage() {
    bodyPages = new HashMap<>();

    int counter = 0;

    for (int i = 0; i < generatorFXList.size(); i++) {
      if (i % BodyGeneratorPos.PAGE_MAX.value() == 0) {

        bodyPages.put("page" + counter, new BodyGenerators(generatorFXList, i));

        counter++;
      }
    }

    mainBody.getChildren().clear();
    mainBody.getChildren().add(bodyPages.get("page0"));
  }
}
