package com.jubble.app.utils;

import com.jubble.app.components.generator.Generator;

import java.util.List;

/**
 * Defines all the data that should be saved in the JSON file.
 * An object of type GameProgress will be passed to the class GameProgressHandler.
 * */
public class GameProgress {
    private List<Generator> allOwnedGenerators;
    private double balance;

    /**
     * @param allOwnedGenerators list of owned generators.
     * @param balance value of game balance.
     * */
    public GameProgress(List<Generator> allOwnedGenerators, double balance) {
        this.allOwnedGenerators = allOwnedGenerators;
        this.balance = balance;
    }

    /**
     * @return list of owned generator.
     * */
    public List<Generator> getAllOwnedGenerators() {
        return allOwnedGenerators;
    }

    /**
     * @return balance.
     * */
    public double getBalance() {
        return balance;
    }
}
