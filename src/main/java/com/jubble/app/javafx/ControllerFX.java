package com.jubble.app.javafx;

import com.jubble.app.ThreadRunner;
import com.jubble.app.components.Balance;
import com.jubble.app.javafx.pages.bodies.BodyGenerators;
import com.jubble.app.javafx.tasks.BalanceTask;
import com.jubble.app.javafx.tasks.CostNextTask;
import com.jubble.app.javafx.tasks.NumberOwnedTask;
import com.jubble.app.javafx.tasks.ProductionTask;
import com.jubble.app.utils.Settings;
import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ControllerFX implements Initializable {

  /** Contains the instance of the game balance. This variable is shared between the threads. */
  private final Balance bal = ThreadRunner.getGameBalance();
  /**
   * Contains 3 labels for each generator: <br>
   * 1. Name of the generator; 2. The production of the generator; 3. Next cost of the generator.
   */
  private List<List<Label>> generatorLabels;

  private List<ImageView> generatorImageViews;

  private Map<String, VBox> bodyPages;

  /**
   * Each of the following variables refers to an existing javafx object which is contained in FXML
   * file and it has an ID equals to the name of these variables
   */
  @FXML private FlowPane main_page_container;

  @FXML private VBox main_body;

  @FXML private Label balanceLabel;

  @FXML private VBox shopPanel;

  @FXML private GridPane shopGrid;

  @FXML private GridPane pageGrid;

  @FXML private Label totalProductionLabel;

  /** this method set the shop panel visible */
  @FXML
  public void displayShop() {
    shopPanel.setVisible(true);
  }

  /** this method set the shop panel hidden */
  @FXML
  public void hideShop() {
    shopPanel.setVisible(false);
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

    bodyPages = new HashMap<>();

    generatorLabels = new ArrayList<>();
    generatorImageViews = new ArrayList<>();

    shopPanel.setVisible(false);

    generateShop();

    generatePageGenerator();

    main_body.getChildren().clear();
    main_body.getChildren().add(bodyPages.get("page00"));

    BalanceTask balTask = new BalanceTask();

    balanceLabel.textProperty().bind(balTask.messageProperty());

    ThreadTaskUtil.autoBuild(balTask);

    ProductionTask prodTask = new ProductionTask();
    totalProductionLabel.textProperty().bind(prodTask.messageProperty());

    ThreadTaskUtil.autoBuild(prodTask);

    for (int i = 0; i < generatorLabels.size(); i++) {
      CostNextTask costTask = new CostNextTask(i);

      generatorLabels.get(i).get(2).textProperty().bind(costTask.messageProperty());

      ThreadTaskUtil.autoBuild(costTask);

      /*
      NumberOwnedTask nrTask =
          new NumberOwnedTask(i, generatorImageViews.get(i), generatorLabels.get(i).get(3));

      generatorLabels.get(i).get(3).textProperty().bind(nrTask.messageProperty());

      ThreadTaskUtil.autoBuild(nrTask); */

    }
  }


  public void generateShop() {

    final int maxPerRow = 3;

    int length = Settings.getGenerators().size();

    for (int i = 0; i < length; i++) {
      Label n = new Label(Settings.getGenerators().get(i).getName());
      Label p =
          new Label(
              "Production: "
                  + String.format(
                      Locale.US, "%,.2f", Settings.getGenerators().get(i).getProductionBase())
                  + "/s");
      Label c = new Label("Cost: " + Settings.getGenerators().get(i).getNextCost());

      n.getStyleClass().add("generator-title");
      p.getStyleClass().add("generator-desc");
      c.getStyleClass().add("generator-desc");

      ImageView v =
          new ImageView("assets/game-components/generator" + ((i > 9) ? i : ("0" + i)) + ".png");
      v.setFitHeight(58);
      v.setFitWidth(160);

      generatorLabels.add(new ArrayList<>());

      generatorLabels.get(i).add(n);
      generatorLabels.get(i).add(p);
      generatorLabels.get(i).add(c);

      Button b = new Button("Buy");
      b.setId(i + "");
      b.setOnAction(this::buyGenerator);
      b.getStyleClass().add("button-buy");

      VBox vbx = new VBox(6, v, n, p, c, b);
      vbx.setAlignment(Pos.TOP_CENTER);
      VBox.setMargin(v, new Insets(10, 0, 0, 0));
      vbx.setMinHeight(100);

      shopGrid.add(vbx, (i % maxPerRow), (i / maxPerRow));
    }
  }

  public void generatePageGenerator() {

    int length = Settings.getGenerators().size();

    final int max = BodyGenerators.NR_MAX_GENERATORS_PER_PAGE;

    int counter = 0;
/*
    for (int i = 0; i < length; i++) {

      Label numberOwned = new Label("Nr: " + Settings.getGenerators().get(i).getNumberOwned());
      numberOwned.getStyleClass().add("generator-desc");
      numberOwned.setVisible(false);

      ImageView v =
          new ImageView("assets/game-components/generator" + ((i > 9) ? i : ("0" + i)) + ".png");
      v.setFitHeight(70);
      v.setFitWidth(193);
      generatorImageViews.add(v);
      v.setVisible(false);

      VBox vbx = new VBox(2, v, numberOwned);
      vbx.setAlignment(Pos.TOP_CENTER);
      VBox.setMargin(v, new Insets(2, 0, 0, 0));

      generatorLabels.get(i).add(numberOwned);

      pageGrid.add(vbx, (i < maxPerRow ? maxPerRow - 1 : 0), (i % maxPerRow));
    } */

    int maxItimes = ((length / max) + ((length % max == 0) ? 0 : 1));

    for (int i = 0; i < maxItimes; i++) {

      BodyGenerators body = new BodyGenerators();

      /**
       * maxItimes is the number of pages of generators needed when generators number is more than
       * max that is NR_MAX_GENERATORS_PER_PAGE
       *
       * Therefore the outer cycle represents page creation.
       *
       * Inner cycle is the generator creation which must repeat NR_MAX_GENERATORS_PER_PAGE if the
       * number of generators for that page is sufficient to fill it completely or it must repeat
       * only for less times that would be the number of the generators left to put.
       * This case happens when numberOfGenerators % NR_MAX_GENERATORS_PER_PAGE != 0
       *
       */
      for(int j = 0; j < ((((maxItimes == (length / max)) ? maxItimes :(maxItimes - 1)) == i) ? (length % max) : max); j++) {

        body.addGenerator(counter, "assets/game-components/generator" + ((counter > 9) ? counter : ("0" + counter)) + ".png");

        counter++;
      }

      body.buildPage();

      bodyPages.put(("page" + ((i > 9) ? i : ("0" + i))), body);

    }
  }

  public void buyGenerator(ActionEvent event) {

    Button b = (Button) event.getSource();
    int id = Integer.parseInt(b.getId());

    if (bal.getPrimary() > Settings.getGenerators().get(id).getNextCost()) {
      bal.setPrimary(bal.getPrimary() - Settings.getGenerators().get(id).getNextCost());
      Settings.getGenerators().get(id).incrementNumberOwned();
    }
  }
}
