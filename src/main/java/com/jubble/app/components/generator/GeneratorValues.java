package com.jubble.app.components.generator;

/**
 * Describes the values of a generator. Each generator generates a certain amount of currency. Every
 * type of generator represents a progression in the game.
 */
public class GeneratorValues {
  private final double costBase;
  private final double productionBase;
  private final double rateGrowth;

  /**
   * @param costBase base cost of a generator.
   * @param productionBase production base.
   * @param rateGrowth rate of growth of a generator.
   */
  public GeneratorValues(double costBase, double productionBase, double rateGrowth) {
    this.costBase = costBase;
    this.productionBase = productionBase;
    this.rateGrowth = rateGrowth;
  }

  /** @return costBase. */
  public double getCostBase() {
    return costBase;
  }

  /** @return productionBase. */
  public double getProductionBase() {
    return productionBase;
  }

  /** @return rateGrowth. */
  public double getRateGrowth() {
    return rateGrowth;
  }
}
