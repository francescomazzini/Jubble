package com.jubble.app.components.generator;

public class GeneratorValues {
  private final double costBase;
  private final double productionBase;
  private final double rateGrowth;

  /**
   * Describes the values of a generator. Each generator generates a certain amount of currency.
   * Every type of generator represents a progression in the game.
   */
  public GeneratorValues(double costBase, double productionBase, double rateGrowth) {
    this.costBase = costBase;
    this.productionBase = productionBase;
    this.rateGrowth = rateGrowth;
  }

  public double getCostBase() {
    return costBase;
  }

  public double getProductionBase() {
    return productionBase;
  }

  public double getRateGrowth() {
    return rateGrowth;
  }
}
