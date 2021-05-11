package com.jubble.app.classes;

public class Currency {
    private String name;
    private double owned;

    /**
     * This represent the currency of the game.
     * @param name of the currency.
     * */
    public Currency(String name) {
        this.name = name;
        owned = 0;
    }

    /**
     * Set amount of owned currency.
     * @param owned number of currency to set.
     * */
    public void setOwned(double owned) {
        this.owned = owned;
    }

    /**
     * Get amount of owned currency.
     * */
    public double getOwned() {
        return owned;
    }
}
