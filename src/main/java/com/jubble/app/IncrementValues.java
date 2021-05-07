package com.jubble.app;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TimerTask;

public class IncrementValues extends TimerTask {
   // Number of owned generators
    private int ownedNumber = 0;
    // Represents a generator level
    private int multiplyer = 1;
    // Game currency
    private BigDecimal currency = new BigDecimal(0);

    /**
     * Increment number of owned generators
     * */
    public void incrementOwnedNumber() {
        ownedNumber++;
    }

    private BigDecimal computeNextCost() {
        final int INITIAL_COST = 4;
        final double RATE_GROWTH = 1.07;
        BigDecimal cost = new BigDecimal(INITIAL_COST * Math.pow(RATE_GROWTH, ownedNumber));
        return cost;
    }

    private BigDecimal computeProductionTotal() {
        final double PRODUCITON_BASE = 1.67;
        BigDecimal producitonTotal = new BigDecimal(PRODUCITON_BASE * ownedNumber);
        return producitonTotal;
    }


    /**
     * Increment game values
     * */
    @Override
    public void run() {
        Observer actionObserver = new Observer();
        System.out.println(actionObserver.getAction());
        incrementOwnedNumber();
        System.out.println(computeNextCost());
        System.out.println(computeProductionTotal());
    }
}
