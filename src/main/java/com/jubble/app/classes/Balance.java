package com.jubble.app.classes;

public class Balance {
    private static Currency primary = Settings.getCurrencies().get(0);

    public static double getPrimary() {
        return primary.getOwned();
    }

    public static void setPrimary(double amount) {
        primary.setOwned(amount);
    }

    //public static void minusPrimary (double amount) {setPrimary(getPrimary() - amount);}
}
