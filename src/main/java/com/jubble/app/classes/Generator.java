package com.jubble.app.classes;

public class Generator {

    private String name;
    private int numberOwned;
    private int multiplier;
    private final double PRODUCTIONBASE;
    private final double COSTBASE;

    private static final double RATEGROWTH = 1.07;

    public Generator(String name, double COSTBASE, double PRODUCTIONBASE) {
        numberOwned = 0;
        multiplier = 1;
        this.name = name;
        this.COSTBASE = COSTBASE;
        this.PRODUCTIONBASE = PRODUCTIONBASE;
    }

    public double getNextCost() {
        return COSTBASE * Math.pow(RATEGROWTH, numberOwned);
    }

    public double getProduction() {
        return (PRODUCTIONBASE * numberOwned) * multiplier;
    }

}
