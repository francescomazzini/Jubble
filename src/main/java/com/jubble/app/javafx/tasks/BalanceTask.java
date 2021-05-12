package com.jubble.app.javafx.tasks;


import com.jubble.app.components.Balance;
import com.jubble.app.utils.Settings;
import javafx.concurrent.Task;

public class BalanceTask extends Task<Void> {

    @Override
    protected Void call() throws Exception {
        while(true) {

            Thread.sleep(500);
            updateProgress(Settings.getCurrencies().get(0).getOwned(), 0);

        }
    }

    @Override
    protected void updateProgress(double v, double v1) {
        updateMessage(String.format("%.2f", v) +" ");
        super.updateProgress(v, v1);
    }
}
