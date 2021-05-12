package com.jubble.app.javafx;

import com.jubble.app.App;
import com.jubble.app.components.Balance;
import com.jubble.app.javafx.tasks.BalanceTask;
import com.jubble.app.javafx.tasks.CostNextTask;
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

    private Balance bal = App.getGameBalance();
    private List<List<Label>> generator_labels;

    @FXML
    private Label label_balance;

    @FXML
    private VBox shop_panel;

    @FXML
    private GridPane grid_shop;

    @FXML
    public void displayShop() {
        shop_panel.setVisible(true);
    }

    @FXML
    public void hideShop() {
        shop_panel.setVisible(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        shop_panel.setVisible(false);

        generateShop();

        BalanceTask balTask = new BalanceTask();


        label_balance.textProperty().bind(balTask.messageProperty());

        Thread t = new Thread(balTask);
        t.setDaemon(true);
        t.start();

        for(int i = 0; i < generator_labels.size(); i++) {
            CostNextTask costTask = new CostNextTask(i);

            generator_labels.get(i).get(2).textProperty().bind(costTask.messageProperty());

            Thread t2 = new Thread(costTask);
            t2.setDaemon(true);
            t2.start();
        }

    }

    public void generateShop() {

        generator_labels = new ArrayList<>();

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
