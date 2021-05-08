package com.jubble.app;

import java.util.Timer;

public class GameRunner {
    public void run() {
        Timer timer = new Timer();

        Observable gameActionObservable = new Observable();
        IncrementValues game = new IncrementValues();

        GameAction action = new Observer();
        gameActionObservable.attach(action);
        gameActionObservable.setAction("State1");
        gameActionObservable.setAction("State2");
    }
}
