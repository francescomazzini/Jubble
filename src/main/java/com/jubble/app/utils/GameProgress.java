package com.jubble.app.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jubble.app.components.generator.Generator;
import java.util.List;

/**
 * Defines all the data that should be saved in the JSON file. An object of type GameProgress will
 * be passed to the class GameProgressHandler.
 */
public class GameProgress {
  private List<Generator> allOwnedGenerators;
  private double balance;

  /**
   * @param allOwnedGenerators list of owned generators.
   * @param balance value of game balance.
   */
  @JsonCreator
  public GameProgress(
      @JsonProperty("allOwnedGenerators") List<Generator> allOwnedGenerators,
      @JsonProperty("balance") double balance) {
    this();
    this.allOwnedGenerators = allOwnedGenerators;
    this.balance = balance;
  }

  private GameProgress() {}

  /** @return list of owned generator. */
  public List<Generator> getAllOwnedGenerators() {
    return allOwnedGenerators;
  }

  /** @return balance. */
  public double getBalance() {
    return balance;
  }

  public void setAllOwnedGenerators(List<Generator> allOwnedGenerators) {
    this.allOwnedGenerators = allOwnedGenerators;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }
}
