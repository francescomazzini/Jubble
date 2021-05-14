package com.jubble.app.javafx.tasks;

import com.jubble.app.utils.Settings;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.Locale;

public class NumberOwnedTask extends Task<Void> {

    private int generator;
    private ImageView image;
    private Label label;

    public NumberOwnedTask (int i, ImageView image, Label label) {
        this.generator = i;
        this.image = image;
        this.label = label;
    }

    @Override
    protected Void call() throws Exception {
        while(true) {

            Thread.sleep(500);
            updateProgress(Settings.getGenerators().get(generator).getNumberOwned(), 0);

            if(Settings.getGenerators().get(generator).getNumberOwned() > 0){
                image.setVisible(true);
                label.setVisible(true);
            }

        }
    }

    @Override
    protected void updateProgress(double v, double v1) {
        updateMessage("Qt: " + (int) v);
        super.updateProgress(v, v1);
    }

}
