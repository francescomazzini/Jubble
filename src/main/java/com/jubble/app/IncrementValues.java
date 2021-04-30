package com.jubble.app;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TimerTask;

public class IncrementValues extends TimerTask {
    // Increments every second this thread runs
    private int index = 1;
    // Number of owned generators
    private int ownedNumber = 0;
    // Represents a generator level
    private int multiplyer = 1;

    /**
     * Increment number of owned generators
     * */
    public void incrementOwnedNumber() {
        ownedNumber++;
    }

    private void computeNextCost() {
        final int INITIAL_COST = 4;
        final double RATE_GROWTH = 1.07;
        BigDecimal cost = new BigDecimal(INITIAL_COST * Math.pow(RATE_GROWTH, ownedNumber));
        System.out.println(cost);
    }

    private void computeProductionTotal() {
        final double PRODUCITON_BASE = 1.67;
        BigDecimal producitonTotal = new BigDecimal(PRODUCITON_BASE * ownedNumber);
        System.out.println(producitonTotal);
    }


    /**
     * Increment game values
     * */
    @Override
    public void run() {
        int i = 0;
        System.out.println("Running " + index);
        computeNextCost();
        incrementOwnedNumber();
        computeProductionTotal();
        index++;
    }
}
