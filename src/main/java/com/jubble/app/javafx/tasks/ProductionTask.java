package com.jubble.app.javafx.tasks;


import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.utils.Settings;
import javafx.concurrent.Task;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class ProductionTask extends AbstractTask {

    @Override
    protected Void call() throws Exception {
        while(true) {

            Thread.sleep(500);
            updateProgress(Settings.getGenerators().stream().mapToDouble(Generator::getProduction).sum(), 0);

        }
    }


    @Override
    protected void updateProgress(double v, double v1) {
        updateMessage(String.format(Locale.US, "%,.2f", v)+ "/s ");
        super.updateProgress(v, v1);
    }

}
