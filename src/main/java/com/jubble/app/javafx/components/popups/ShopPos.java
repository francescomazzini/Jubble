package com.jubble.app.javafx.components.popups;

public enum ShopPos {
    ROW_GENERATOR_MAX(3);

    private final int value;


    public int value() {
        return value;
    }

    ShopPos(int value) {
        this.value = value;
    }
}
