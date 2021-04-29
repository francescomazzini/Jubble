package com.jubble.app;

import java.util.Date;
import java.util.TimerTask;

public class IncrementValues extends TimerTask {

    /**
     * Increment game values
     * */
    @Override
    public void run() {
        System.out.println("Running " + new Date());
    }
}
