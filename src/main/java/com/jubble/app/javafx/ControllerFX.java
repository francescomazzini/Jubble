package com.jubble.app.javafx;

import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.components.generator.GeneratorsSingleton;
import com.jubble.app.javafx.components.BalanceFX;
import com.jubble.app.javafx.components.GeneratorFX;
import com.jubble.app.javafx.components.bodiesMainPage.BodyGenerators;
import com.jubble.app.javafx.components.popups.ShopGenerator;
import com.jubble.app.javafx.tasks.BalanceTask;
import com.jubble.app.javafx.tasks.ProductionTask;
import java.net.URL;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class ControllerFX implements Initializable {
  /**
   * Contains 3 labels for each generator: <br>
   * 1. Name of the generator; 2. The production of the generator; 3. Next cost of the generator.
   * 4. Number Owned Of That Generator
   */

  private Map<String, VBox> bodyPages;

  private static List<GeneratorFX> generatorFXList;

  private int pageSelected;

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

  /** this method set the shop panel visible */
  @FXML
  public void displayShop() {
    shopPanelContainer.setVisible(true);
    shopPanel.setVisible(true);
  }

  /** this method set the shop panel hidden */
  @FXML
  public void hideShop() {
    shopPanelContainer.setVisible(false);
    shopPanel.setVisible(false);
  }

  @FXML
  public void switchPageLeft() {
    switchPage(pageSelected + 1);
  }

  @FXML
  public void switchPageRight() {
    switchPage(pageSelected - 1);
  }

  /**
   * Replaces the constructor of the controller which cannot have a constructor. It is meant to ran
   * before the GUI is shown to the user.
   *
   * <p>What is done in this method: - the shop panel is set hidden by default - it calls {@link
   * #generateShop()} which generates the shop panel - it binds the text property of the label to
   * the task BalanceTask and it puts this task in a new Thread and start it after set it as Daemon
   * (it stops if the main thread is stopped)
   *
   * @param url not used
   * @param resourceBundle not used
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    setUpBalanceFX();
    setUpGeneratorFX();
    generateShop();
    generatePageGenerator();

  }

  public void setUpGeneratorFX() {

    generatorFXList = new ArrayList<>();

    List<Generator> generatorList = GeneratorsSingleton.getGenerators();

    for (int i = 0; i < generatorList.size(); i++) {
      Generator currentGenerator = generatorList.get(i);

      generatorFXList.add(new GeneratorFX(currentGenerator,
                          "assets/game-components/generator" + i + ".png")
      );

    }

  }

  public void setUpBalanceFX() {
    BalanceFX balanceFX = new BalanceFX(balanceLabel, totalProductionLabel);
  }

  public void generateShop() {

    ShopGenerator shop = new ShopGenerator(3, generatorFXList );

    shop.generateShopPanel();

    shopAnchorPane.getChildren().clear();
    shopAnchorPane.getChildren().add(shop);

    int nGenerators = GeneratorsSingleton.getGenerators().size();
    shopAnchorPane.setMinHeight(220 * Math.ceil(nGenerators / 3.0));

    hideShop();
  }

  public void generatePageGenerator() {

    pageSelected = 0;
    bodyPages = new HashMap<>();

    int counter = 0;

    for(int i = 0; i < generatorFXList.size(); i++) {
      if(i % BodyGenerators.NR_MAX_GENERATORS_PER_PAGE == 0) {

        bodyPages.put("page" + counter, new BodyGenerators(generatorFXList, i));

        counter++;
      }
    }

    mainBody.getChildren().add(bodyPages.get("page0"));

  }

  public void switchPage(int newPageNumber) {
    String namePageWanted = "page" + newPageNumber;

    BodyGenerators bodyWanted = (BodyGenerators) bodyPages.get(namePageWanted);

    if (bodyWanted != null)
      if (bodyWanted.areThereGeneratorsVisible()) {
        mainBody.getChildren().clear();
        mainBody.getChildren().add(bodyWanted);
        this.pageSelected = newPageNumber;
      }
  }

}
