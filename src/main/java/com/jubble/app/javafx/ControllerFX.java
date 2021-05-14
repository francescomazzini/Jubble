package com.jubble.app.javafx;

import com.jubble.app.App;
import com.jubble.app.components.Balance;
import com.jubble.app.javafx.tasks.BalanceTask;
import com.jubble.app.javafx.tasks.CostNextTask;
import com.jubble.app.javafx.tasks.NumberOwnedTask;
import com.jubble.app.javafx.tasks.ProductionTask;
import com.jubble.app.utils.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ControllerFX implements Initializable {

    /**
     * bal is the istance of the game balance which must be an object
     * because it is used by multiple threads
     *
     * generator_labels contains 3 labels for each generator, the first label (nr. 0)
     * is the name of the generator, the second (nr. 1) is the production of the
     * generator and the third (nr. 2) is the next cost of the generator
     */
    private Balance bal = App.getGameBalance();
    private List<List<Label>> generator_labels;
    private List<ImageView> generator_imageViews;

    /**
     * Each of the following variables refers to an existing javafx object which
     * is contained in FXML file and it has an ID equals to the name of these variables
     */
    @FXML
    private Label label_balance;

    @FXML
    private VBox shop_panel;

    @FXML
    private GridPane grid_shop;

    @FXML
    private GridPane grid_page;

    @FXML
    private Label label_production_total;

    /**
     * this method set the shop panel visible
     */
    @FXML
    public void displayShop() {
        shop_panel.setVisible(true);
    }

    /**
     * this method set the shop panel hidden
     */
    @FXML
    public void hideShop() {
        shop_panel.setVisible(false);
    }

    /**
     * This method replaces the constructor of the controller which cannot have a constructor.
     * It is meant to be runned before the GUI is shown to the user.
     *
     * What is done in this method:
     *  - the shop panel is set hidden by default
     *  - it calls @generateShop which generates the shop panel
     *  - it binds the text property of the label to the task BalanceTask
     *    and it puts this task in a new Thread and start it after set it as Deamon (it stops if the main thread is stopped)
     *  -
     *
     * @param url not used
     * @param resourceBundle not used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        generator_labels = new ArrayList<>();
        generator_imageViews = new ArrayList<>();

        shop_panel.setVisible(false);

        generateShop();

        generatePageGenerator();

        BalanceTask balTask = new BalanceTask();

        label_balance.textProperty().bind(balTask.messageProperty());

        Thread t = new Thread(balTask);
        t.setDaemon(true);
        t.start();

        ProductionTask prodTask = new ProductionTask();

        label_production_total.textProperty().bind(prodTask.messageProperty());

        Thread t0 = new Thread(prodTask);
        t0.setDaemon(true);
        t0.start();

        for(int i = 0; i < generator_labels.size(); i++) {
            CostNextTask costTask = new CostNextTask(i);

            generator_labels.get(i).get(2).textProperty().bind(costTask.messageProperty());

            Thread t2 = new Thread(costTask);
            t2.setDaemon(true);
            t2.start();



            NumberOwnedTask nrTask = new NumberOwnedTask(i, generator_imageViews.get(i), generator_labels.get(i).get(3));

            generator_labels.get(i).get(3).textProperty().bind(nrTask.messageProperty());

            Thread t3 = new Thread(nrTask);
            t3.setDaemon(true);
            t3.start();

        }

    }

    public void generateShop() {

        final int maxPerRow = 3;

        int length = Settings.getGenerators().size();

        for (int i = 0; i < length; i++) {
            Label n = new Label(Settings.getGenerators().get(i).getName());
            Label p = new Label("Production: " + String.format(Locale.US,"%,.2f", Settings.getGenerators().get(i).getProductionBase() )+ "/s");
            Label c = new Label("Cost: " + Settings.getGenerators().get(i).getNextCost());

            n.getStyleClass().add("generator-title");
            p.getStyleClass().add("generator-desc");
            c.getStyleClass().add("generator-desc");


            ImageView v = new ImageView("assets/game-components/generator"+ ((i > 9) ? i : ("0"+i))+".png");
            v.setFitHeight(58);
            v.setFitWidth(160);

            generator_labels.add(new ArrayList<>());

            generator_labels.get(i).add(n);
            generator_labels.get(i).add(p);
            generator_labels.get(i).add(c);

            Button b = new Button("Buy");
            b.setId(i+"");
            b.setOnAction(this::buyGenerator);
            b.getStyleClass().add("button-buy");

            VBox vbx = new VBox(6, v, n, p, c, b );
            vbx.setAlignment(Pos.TOP_CENTER);
            vbx.setMargin(v, new Insets(10, 0, 0, 0));
            vbx.setMinHeight(100);

            grid_shop.add(vbx, (i % maxPerRow), (i / maxPerRow));

        }
    }

    public void generatePageGenerator() {

        int length = Settings.getGenerators().size();

        final int maxPerRow = 3;

        for (int i = 0; i < length; i++) {

            Label numberOwned = new Label("Nr: " + Settings.getGenerators().get(i).getNumberOwned());
            numberOwned.getStyleClass().add("generator-desc");
            numberOwned.setVisible(false);

            ImageView v = new ImageView("assets/game-components/generator"+ ((i > 9) ? i : ("0"+i))+".png");
            v.setFitHeight(70);
            v.setFitWidth(193);
            generator_imageViews.add(v);
            v.setVisible(false);

            VBox vbx = new VBox(2, v, numberOwned);
            vbx.setAlignment(Pos.TOP_CENTER);
            vbx.setMargin(v, new Insets(2, 0, 0, 0));
            //vbx.setMinHeight(100);

            generator_labels.get(i).add(numberOwned);

            grid_page.add(vbx, (i < maxPerRow ? maxPerRow-1 : maxPerRow - maxPerRow), (i % maxPerRow));
        }

    }


    public void buyGenerator(ActionEvent event) {

        Button b = (Button) event.getSource();
        int id = Integer.parseInt(b.getId());

        if (bal.getPrimary() > Settings.getGenerators().get(id).getNextCost()) {
            bal.setPrimary(
                    bal.getPrimary() - Settings.getGenerators().get(id).getNextCost());
            Settings.getGenerators().get(id).incrementNumberOwned();
        }
    }

}
