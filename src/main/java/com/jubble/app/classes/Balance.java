package com.jubble.app.classes;

import com.jubble.app.setting.Settings;

public class Balance {
    private Currency primary;

    public Balance () {
        this.primary = Settings.getCurrencies().get(0);
    }

    synchronized public double getPrimary() {
        return primary.getOwned();
    }

    synchronized public  void setPrimary(double amount) {
        primary.setOwned(amount);
    }

}
