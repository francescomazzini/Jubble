package com.jubble.app.core.resources.generator;

/**
 * Represents a generator in the game. Implements the builder pattern and can be instantiated only
 * providing a builder.
 */
public final class Generator extends GeneratorBody {

  private Generator(Builder builder) {
    super(builder);
  }

  /** Concrete builder for this class. Implementation of the builder pattern. */
  public static class Builder extends GeneratorBody.Builder<Builder> {

    @Override
    public Generator build() {
      return new Generator(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }

  /** When called increments the number owned of this generator by 1. */
  public void incrementNumberOwned() {
    super.setNumberOwned(getNumberOwned() + 1);
  }

  /**
   * Return the number of generator owned for this generator.
   *
   * @return numberOwned.
   */
  public int getNumberOwned() {
    return super.getNumberOwned();
  }

  /**
   * Sets the number owned of this generator. It can be called only at the beginning of the game,
   * when number owned has still default values. Not doing so, would represent by cheating by a
   * method and would throw an exception.
   *
   * @param numberOwned value to be set.
   * @throws IllegalOperationException if the game is already running.
   */
  public void setNumberOwned(int numberOwned) {
    IllegalOperationException.checkIfNumberOwnedIsDefault(getNumberOwned());
    super.setNumberOwned(numberOwned);
  }

  /**
   * Computes the cost of the next generator.
   *
   * @return next cost.
   */
  public double getNextCost() {
    return super.getCostBase() * Math.pow(super.getRateGrowth(), super.getNumberOwned());
  }

  /**
   * Computes the total production of a generator.
   *
   * @return total production.
   */
  public double getProduction() {
    return (super.getProductionBase() * super.getNumberOwned());
  }

  /**
   * Returns the production base.
   *
   * @return productionBase.
   */
  public double getProductionBase() {
    return super.getProductionBase();
  }

  /**
   * Returns the name of this generator.
   *
   * @return name.
   */
  public String getName() {
    return super.getName();
  }

  /**
   * Tells if numberOwned is more than default value 0.
   *
   * @return true if numberOwned is different than default.
   */
  public boolean isMoreThanZeroOwned() {
    return getNumberOwned() != DEFAULT_NUMBER_OWNED_GENERATORS;
  }
}
