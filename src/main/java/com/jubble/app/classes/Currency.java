package com.jubble.app.classes;

public class Currency {
    private String name;
    private double owned;

    public Currency(String name) {
        this.name = name;
        owned = 0;
    }

    public void setOwned(double owned) {
        this.owned = owned;
    }

    public double getOwned() {
        return owned;
    }
}
