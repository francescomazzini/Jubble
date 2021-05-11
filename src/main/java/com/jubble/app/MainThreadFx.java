package com.jubble.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainThreadFx extends Application implements Runnable {


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/../resources/graphic.fxml"));
        primaryStage.setTitle("Jubble");
        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.show();

    }

    @Override
    public void run() {

    }


}
