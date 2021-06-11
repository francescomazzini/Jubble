package com.jubble.app.core.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Defines all the data that should be saved in the JSON file. An object of type GameProgress will
 * be passed to the class GameProgressHandler.
 */
public class GameProgress {
  private List<Integer> ownedGenerators;
  private double balance;

  /**
   * @param ownedGenerators list of owned generators.
   * @param balance value of game balance.
   */
  @JsonCreator
  public GameProgress(
      @JsonProperty("ownedGenerators") List<Integer> ownedGenerators,
      @JsonProperty("balance") double balance) {
    this.ownedGenerators = ownedGenerators;
    this.balance = balance;
  }

  /** @return list of owned generator. */
  public List<Integer> getOwnedGenerators() {
    return ownedGenerators;
  }

  /** @return balance. */
  public double getBalance() {
    return balance;
  }

  public void setOwnedGenerators(List<Integer> ownedGenerators) {
    this.ownedGenerators = ownedGenerators;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }
}
