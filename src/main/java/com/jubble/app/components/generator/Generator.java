package com.jubble.app.components.generator;

public class Generator {
  private GeneratorID id;
  private int numberOwned;
  private int multiplier;
  private final double PRODUCTIONBASE;
  private final double COSTBASE;
  private final double RATEGROWTH;

  public Generator(
      GeneratorID id, double COSTBASE, double PRODUCTIONBASE, double RATEGROWTH) {
    // Default the player does not know any generator.
    numberOwned = 0;
    // M
    multiplier = 1;
    this.id = id;
    this.COSTBASE = COSTBASE;
    this.PRODUCTIONBASE = PRODUCTIONBASE;
    this.RATEGROWTH = RATEGROWTH;
  }

  public void incrementNumberOwned() {
    numberOwned++;
  }

  public int getNumberOwned() {
    return numberOwned;
  }

  public double getNextCost() {
    return COSTBASE * Math.pow(RATEGROWTH, numberOwned);
  }

  public double getProduction() {
    return (PRODUCTIONBASE * numberOwned) * multiplier;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return id.toString() + " " + numberOwned;
  }
}
