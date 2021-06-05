package com.jubble.app.javafx;

import com.jubble.app.ThreadRunner;
import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.javafx.pages.bodies.BodyGenerators;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class ControllerFX implements Initializable {

  /** Contains the instance of the game balance. This variable is shared between the threads. */
  private final Balance bal = ThreadRunner.getGameBalance();
  /**
   * Contains 3 labels for each generator: <br>
   * 1. Name of the generator; 2. The production of the generator; 3. Next cost of the generator.
   */
  private List<List<Label>> generatorLabelsManager;
  private List<VBox> generatorVBoxManager;

  private Map<String, VBox> bodyPages;

  private int pageSelected;

  /**
   * Each of the following variables refers to an existing javafx object which is contained in FXML
   * file and it has an ID equals to the name of these variables
   */
  @FXML private Button left_arrow;

  @FXML private ScrollPane scroll_pane_shop;

  @FXML private AnchorPane anchor_pane_shop;

  @FXML private VBox main_body;

  @FXML private Label balanceLabel;

  @FXML private VBox shopPanel;

  @FXML private GridPane pageGrid;

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
    main_body.getChildren().add(bodyPages.get("page00"));

    BalanceTask balTask = new BalanceTask();

    balanceLabel.textProperty().bind(balTask.messageProperty());

    ThreadTaskUtil.autoBuild(balTask);

    ProductionTask prodTask = new ProductionTask();
    totalProductionLabel.textProperty().bind(prodTask.messageProperty());

    ThreadTaskUtil.autoBuild(prodTask);


    hideShop();
  }

  public void generateShop() {

    GridPane shopGrid = new GridPane();

    final int maxPerRow = 3;

    int length = Assets.getInstance().getGenerators().size();

    for (int i = 0; i < maxPerRow; i++) {
      ColumnConstraints column = new ColumnConstraints();
      column.setPercentWidth(100 / maxPerRow);
      shopGrid.getColumnConstraints().add(column);
    }

    for (int i = 0; i < (length / 3 + ((length % 3 == 0) ? 0 : 1)); i++) {
      RowConstraints row = new RowConstraints(220);
      shopGrid.getRowConstraints().add(row);
    }

    for (int i = 0; i < length; i++) {
      Generator currentGenerator = Assets.getInstance().getGenerators().get(i);

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

      ImageView v =
          new ImageView("assets/game-components/generator" + i + ".png");
      v.setFitHeight(58);
      v.setFitWidth(160);

      //NEW MANAGER LETS TRY
      generatorLabelsManager.add(new ArrayList<>());

      generatorLabelsManager.get(i).add(nameGeneratorLabel);
      generatorLabelsManager.get(i).add(productionGeneratorLabel);
      generatorLabelsManager.get(i).add(costGeneratorLabel);

      Button b = new Button("Buy");
      b.setId(i + "");
      b.setOnAction(this::buyGenerator);
      b.getStyleClass().add("button-buy");

      VBox topPadding = new VBox();
      topPadding.setMinHeight(25);

      VBox botPadding = new VBox();
      botPadding.setMinHeight(15);

      VBox vbx = new VBox(topPadding, v, nameGeneratorLabel, productionGeneratorLabel, costGeneratorLabel, botPadding, b);
      vbx.setAlignment(Pos.TOP_CENTER);
      vbx.setMinHeight(100);

      shopGrid.add(vbx, (i % maxPerRow), (i / maxPerRow));
    }

    anchor_pane_shop.getChildren().clear();
    anchor_pane_shop.getChildren().add(shopGrid);
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

      bodyPages.put(("page" + ((i > 9) ? i : ("0" + i))), body);
    }
  }

  public void buyGenerator(ActionEvent event) {

    Button b = (Button) event.getSource();
    int id = Integer.parseInt(b.getId());

    Generator currentGenerator = Assets.getInstance().getGenerators().get(id);

    if (bal.getPrimary() > Assets.getInstance().getGenerators().get(id).getNextCost()) {
      bal.setPrimary(bal.getPrimary() - Assets.getInstance().getGenerators().get(id).getNextCost());
      currentGenerator.incrementNumberOwned();
    }

    generatorLabelsManager.get(id).get(2).setText("Cost: " + String.format(Locale.US, "%,.2f", currentGenerator.getNextCost()));
    generatorLabelsManager.get(id).get(3).setText("Qt: " + currentGenerator.getNumberOwned());

    if(currentGenerator.getNumberOwned() > 0)
      generatorVBoxManager.get(id).setVisible(true);

  }

  public void switchPage(boolean left) {

    int nrPageWanted;

    if(left)
      nrPageWanted = pageSelected + 1;
    else
      nrPageWanted = pageSelected - 1;

    String pageWanted = "page" + ((nrPageWanted > 9) ? nrPageWanted : ("0" + nrPageWanted));
    BodyGenerators body = (BodyGenerators) bodyPages.get(pageWanted);

    if (body != null)
      if (body.areThereGeneratorsVisible())
        if(left)
         pageSelected++;
        else
          pageSelected--;

    pageWanted = "page" + ((pageSelected > 9) ? pageSelected : ("0" + pageSelected));
    main_body.getChildren().clear();
    main_body.getChildren().add(bodyPages.get(pageWanted));

  }

}
