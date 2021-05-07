package com.jubble.app;

public class Observer implements GameAction {
    private String action;

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    @Override
    public void update(String action) {
        this.setAction(action);
        System.out.println("Observer updated with value: " + getAction());
    }
}
