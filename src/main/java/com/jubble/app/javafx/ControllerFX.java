package com.jubble.app.javafx;

import com.jubble.app.ThreadRunner;
import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.javafx.components.bodiesMainPage.BodyGenerators;
import com.jubble.app.javafx.components.popups.ShopGenerator;
import com.jubble.app.javafx.tasks.BalanceTask;
import com.jubble.app.javafx.tasks.ProductionTask;
import com.jubble.app.utils.Assets;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class ControllerFX implements Initializable {

  /** Contains the instance of the game balance. This variable is shared between the threads. */
  private final Balance bal = ThreadRunner.getGameBalance();
  /**
   * Contains 3 labels for each generator: <br>
   * 1. Name of the generator; 2. The production of the generator; 3. Next cost of the generator.
   * 4. Number Owned Of That Generator
   */
  private static List<List<Label>> generatorLabelsManager;
  private static List<VBox> generatorVBoxManager;

  private Map<String, VBox> bodyPages;

  private int pageSelected;

  /**
   * Each of the following variables refers to an existing javafx object which is contained in FXML
   * file and it has an ID equals to the name of these variables
   */

  @FXML private AnchorPane anchor_pane_shop;

  @FXML private VBox main_body;

  @FXML private Label balanceLabel;

  @FXML private VBox shopPanel;

  @FXML private Label totalProductionLabel;

  @FXML private VBox shop_panel_container;

  /** this method set the shop panel visible */
  @FXML
  public void displayShop() {
    shop_panel_container.setVisible(true);
    shopPanel.setVisible(true);
  }

  /** this method set the shop panel hidden */
  @FXML
  public void hideShop() {
    shop_panel_container.setVisible(false);
    shopPanel.setVisible(false);
  }

  @FXML
  public void switchPageLeft() {
    switchPage(true);
  }

  @FXML
  public void switchPageRight() {
    switchPage(false);
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

    pageSelected = 0;

    bodyPages = new HashMap<>();

    generatorLabelsManager = new ArrayList<>();
    generatorVBoxManager = new ArrayList<>();

    shopPanel.setVisible(false);

    generateShop();

    int nGenerators = Assets.getInstance().getGenerators().size();
    anchor_pane_shop.setMinHeight(220 * (nGenerators / 3 + ((nGenerators % 3 == 0) ? 0 : 1)));

    generatePageGenerator();

    main_body.getChildren().clear();
    main_body.getChildren().add(bodyPages.get("page0"));

    BalanceTask balTask = new BalanceTask();

    balanceLabel.textProperty().bind(balTask.messageProperty());

    ThreadTaskUtil.autoBuild(balTask);

    ProductionTask prodTask = new ProductionTask();
    totalProductionLabel.textProperty().bind(prodTask.messageProperty());

    ThreadTaskUtil.autoBuild(prodTask);


    hideShop();
  }

  public void generateShop() {

    ShopGenerator shop = new ShopGenerator(3,
            Assets.getInstance().getGenerators(),
            bal,
            generatorLabelsManager,
            generatorVBoxManager
    );

    shop.generateShopPanel();

    anchor_pane_shop.getChildren().clear();
    anchor_pane_shop.getChildren().add(shop);
  }

  public void generatePageGenerator() {

    int length = Assets.getInstance().getGenerators().size();

    final int max = BodyGenerators.NR_MAX_GENERATORS_PER_PAGE;

    int counter = 0;

    int maxItimes = ((length / max) + ((length % max == 0) ? 0 : 1));

    for (int i = 0; i < maxItimes; i++) {

      BodyGenerators body = new BodyGenerators();

      /**
       * maxItimes is the number of pages of generators needed when generators number is more than
       * max that is NR_MAX_GENERATORS_PER_PAGE
       *
       * <p>Therefore the outer cycle represents page creation.
       *
       * <p>Inner cycle is the generator creation which must repeat NR_MAX_GENERATORS_PER_PAGE if
       * the number of generators for that page is sufficient to fill it completely or it must
       * repeat only for less times that would be the number of the generators left to put. This
       * case happens when numberOfGenerators % NR_MAX_GENERATORS_PER_PAGE != 0
       */
      for (int j = 0;
          j
              < ((((maxItimes == (length / max)) ? maxItimes : (maxItimes - 1)) == i)
                  ? (length % max)
                  : max);
          j++) {

        generatorVBoxManager.add(body.addGenerator(
            counter,
            "assets/game-components/generator"
                + counter
                + ".png"));

        generatorLabelsManager.get(counter).add(
                (Label) generatorVBoxManager.get(counter).getChildren().get(1)
        );

        counter++;
      }

      body.buildPage();

      bodyPages.put("page" + i, body);
    }
  }

  public void switchPage(boolean left) {

    int copyOfPageSelected = pageSelected;

    if(left)
      pageSelected++;
    else
      pageSelected--;

    String namePageWanted = "page" + pageSelected;
    BodyGenerators bodyWanted = (BodyGenerators) bodyPages.get(namePageWanted);

    if (bodyWanted != null)
      if (bodyWanted.areThereGeneratorsVisible()) {
        main_body.getChildren().clear();
        main_body.getChildren().add(bodyWanted);
        return;
      }

    pageSelected = copyOfPageSelected;
  }

}
