package com.jubble.app.javafx.tasks;
import javafx.concurrent.Task;
import com.jubble.app.utils.Settings;

public class CostNextTask extends Task<Void>{

    private int generator;

    public CostNextTask (int i) {
        this.generator = i;
    }

    @Override
    protected Void call() throws Exception {
        while(true) {

            Thread.sleep(500);
            updateProgress(Settings.getGenerators().get(generator).getNextCost(), 0);

        }
    }

    @Override
    protected void updateProgress(double v, double v1) {
        updateMessage("Cost: " + String.format("%.2f", v));
        super.updateProgress(v, v1);
    }

}
