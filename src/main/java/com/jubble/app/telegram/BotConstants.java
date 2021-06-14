package com.jubble.app.telegram;

public enum  BotConstants {
    BOT_TOKEN("1711656042:AAFPvyLcWPSeHwpq7qM-kodBXCYhIKwfKWo"),
    BOT_USERNAME("jubble_bot");

    private final String value;

    public String getValue() {
        return value;
    }

    BotConstants(String value) {
        this.value = value;
    }
}
