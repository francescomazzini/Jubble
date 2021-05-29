package com.jubble.app.components.generator;

public class Generator extends GeneratorID {
  private GeneratorValues values;
  private int numberOwned;

  public Generator(GeneratorID id, GeneratorValues values) {
    super(id);
    // The player does not own any generator at beginning of the game.
    numberOwned = 0;
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
    return (values.getProductionBase() * numberOwned) * super.getLevel();
  }

  public double getProductionBase() {
    return values.getProductionBase();
  }

  public String getName() {
    return super.getName();
  }

  @Override
  public String toString() {
    return super.toString() + " " + numberOwned;
  }
}
