package com.jubble.app.components.generator;

public class Generator extends GeneratorID {
  private GeneratorValues values;
  public static final int DEFAULT_NUMBER_OWNED_GENERATORS = 0;
  private int numberOwned;

  public Generator(GeneratorID id, GeneratorValues values) {
    super(id);
    // The player does not own any generator at beginning of the game.
    numberOwned = DEFAULT_NUMBER_OWNED_GENERATORS;
    this.values = values;
  }

  public void incrementNumberOwned() {
    numberOwned++;
  }

  public int getNumberOwned() {
    return numberOwned;
  }

  public void setNumberOwned(int numberOwned) {
    IllegalOperationException.checkIfNumberOwnedIsDefault(this.numberOwned);
    this.numberOwned = numberOwned;
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

  /**
   * Tells if numberOwned is default.
   *
   * @return true if numberOwned is default.
   */
  public boolean isMoreThanZeroOwned() {
    return numberOwned != DEFAULT_NUMBER_OWNED_GENERATORS;
  }

  @Override
  public String toString() {
    return super.toString() + " " + numberOwned;
  }
}
