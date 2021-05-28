package com.jubble.app.components.generator;

public class Generator {
  private GeneratorID id;
  private GeneratorValues values;
  private int numberOwned;
  private int multiplier;

  public Generator(GeneratorID id, GeneratorValues values) {
    // The player does not own any generator at beginning of the game.
    numberOwned = 0;
    multiplier = 1;
    this.id = id;
    this.values = values;
  }

  public void incrementNumberOwned() {
    numberOwned++;
  }

  public int getNumberOwned() {
    return numberOwned;
  }

  public double getNextCost() {
    return values.getCostBase() * Math.pow(values.getRateGrowth(), numberOwned);
  }

  public double getProduction() {
    return (values.getProductionBase() * numberOwned) * multiplier;
  }

  public double getProductionBase() {
    return values.getProductionBase();
  }

  public String getName() {
    return id.getName();
  }

  @Override
  public String toString() {
    return id.toString() + " " + numberOwned;
  }
}
