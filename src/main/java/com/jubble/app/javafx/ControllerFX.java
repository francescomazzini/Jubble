package com.jubble.app.javafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerFX implements Initializable {
    @FXML
    private Label label_balance;

    public ControllerFX () {
        label_balance = new Label("0 $");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setLabelBalance(String bal) {
        label_balance.setText(bal);
    }

}
