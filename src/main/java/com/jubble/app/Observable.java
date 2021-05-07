package com.jubble.app;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    private String action;
    private List<GameAction> playerActions = new ArrayList<>();

    /**
     * Attach a game action to the observable list.
     * @param action action to attach.
     * */
    public void attach(GameAction action) {
        if(action == null)
            throw new IllegalArgumentException();
        playerActions.add(action);
    }

    /**
     * Detach a game action from the observable list.
     * @param action action to detach.
     * */
    public void detach(GameAction action) {
        if(action == null)
            throw new IllegalArgumentException();
        playerActions.remove(action);
    }

    /**
     * Update the value of an action.
     * @param action new value of observed action.
     * */
    public void setAction(String action) {
        if(action == null || action.isEmpty())
            throw new IllegalArgumentException();
        this.action = action;
        notifyActinChange();
    }

    /**
     * @return action.
     * */
    public String getAction() {
        return action;
    }

    /**
     * Notify currently registered observer about Observable's change.
     * */
    private void notifyActinChange() {
        for (GameAction act : playerActions) {
            act.update(getAction());
        }
    }
}
