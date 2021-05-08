package com.jubble.app;

import com.jubble.app.classes.Balance;
import com.jubble.app.classes.Generator;
import com.jubble.app.classes.Settings;

import java.util.TimerTask;

public class IncrementValues extends TimerTask {
    /**
     * Increment game values
     * */
    @Override
    public void run() {
        Balance.setPrimary(Balance.getPrimary() + Settings.getGenerators().stream().mapToDouble(Generator::getProduction).sum());
    }
}
