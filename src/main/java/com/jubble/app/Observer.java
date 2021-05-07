package com.jubble.app;

public class Observer implements GameAction {
    private String action;

    /**
     * Update the value of an action.
     * @param action new action value.
     * */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return action.
     * */
    public String getAction() {
        return action;
    }

    /**
     * Update Observable object value.
     * @param action value to update.
     * */
    @Override
    public void update(String action) {
        this.setAction(action);
        System.out.println("Observer updated with value: " + getAction());
    }
}
