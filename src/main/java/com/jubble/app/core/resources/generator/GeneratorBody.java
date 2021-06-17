package com.jubble.app.core.resources.generator;

/**
 * Represents a generator in the game. Implements the builder pattern and can be instantiated only
 * providing a builder.
 */
public abstract class GeneratorBody {
  /** Represents the name of this generator. */
  private final String name;
  /** Represents the description of this generator. */
  private final String description;
  /** Represents a level (or progress) in the game. */
  private final int level;

  private final double costBase;
  private final double productionBase;
  private final double rateGrowth;
  public static final int DEFAULT_NUMBER_OWNED_GENERATORS = 0;
  private int numberOwned;

  GeneratorBody(Builder<?> builder) {
    name = builder.name;
    description = builder.description;
    level = builder.level;
    costBase = builder.costBase;
    productionBase = builder.productionBase;
    rateGrowth = builder.rateGrowth;
    numberOwned = builder.numberOwned;
  }

  abstract static class Builder<T extends Builder<T>> {
    // Optional parameters - initialized to default values.
    private String name = "";
    private String description = "";
    private int level = 0;
    private double costBase = 0d;
    private double productionBase = 0d;
    private double rateGrowth = 0d;
    private int numberOwned = DEFAULT_NUMBER_OWNED_GENERATORS;

    /**
     * Set the name.
     *
     * @param val name.
     * @return self.
     */
    public T name(String val) {
      name = val;
      return self();
    }

    /**
     * Set the description.
     *
     * @param val description.
     * @return self.
     */
    public T description(String val) {
      description = val;
      return self();
    }

    /**
     * Set the level.
     *
     * @param val level.
     * @return self.
     */
    public T level(int val) {
      level = val;
      return self();
    }

    /**
     * Set the cost base.
     *
     * @param val costBase.
     * @return self.
     */
    public T costBase(double val) {
      costBase = val;
      return self();
    }

    /**
     * Set the production base.
     *
     * @param val productionBase.
     * @return self.
     */
    public T productionBase(double val) {
      productionBase = val;
      return self();
    }

    /**
     * Set rate growth.
     *
     * @param val rateGrowth.
     * @return self.
     */
    public T rateGrowth(double val) {
      rateGrowth = val;
      return self();
    }

    /**
     * Set number owned.
     *
     * @param val numberOwned.
     * @return self.
     */
    public T numberOwned(int val) {
      numberOwned = val;
      return self();
    }

    /**
     * Builds this class.
     *
     * @return object.
     */
    abstract GeneratorBody build();

    /**
     * Subclasses must override this method to return "this"
     *
     * @return this.
     */
    protected abstract T self();
  }

  /**
   * Returns number owned.
   *
   * @return numberOwned.
   */
  public int getNumberOwned() {
    return numberOwned;
  }

  /**
   * Sets numberOwned.
   *
   * @param numberOwned value to be set.
   */
  public void setNumberOwned(int numberOwned) {
    this.numberOwned = numberOwned;
  }

  /**
   * Returns level.
   *
   * @return level.
   */
  public int getLevel() {
    return level;
  }

  /**
   * Returns name.
   *
   * @return name.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns description.
   *
   * @return description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Returns costBase.
   *
   * @return costBase.
   */
  public double getCostBase() {
    return costBase;
  }

  /**
   * Returns productionBase.
   *
   * @return productionBase.
   */
  public double getProductionBase() {
    return productionBase;
  }

  /**
   * Returns rateGrowth.
   *
   * @return rateGrowth.
   */
  public double getRateGrowth() {
    return rateGrowth;
  }

  @Override
  public String toString() {
    return "Generator: " + name + " level: " + level + " number owned: " + numberOwned;
  }
}
