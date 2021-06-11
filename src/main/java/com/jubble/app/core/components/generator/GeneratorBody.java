package com.jubble.app.core.components.generator;

/**
 * Represents a generator in the game.
 * Implements the builder pattern and can be instantiated only providing a builder.
 */
public abstract class GeneratorBody {
    /**
     * Represents the name of this generator.
     */
    private final String name;
    /**
     * Represents the description of this generator.
     */
    private final String description;
    /**
     * Represents a level (or progress) in the game.
     */
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

        public T name(String val) {
            name = val;
            return self();
        }

        public T description(String val) {
            description = val;
            return self();
        }

        public T level(int val) {
            level = val;
            return self();
        }

        public T costBase(double val) {
            costBase = val;
            return self();
        }

        public T productionBase(double val) {
            productionBase = val;
            return self();
        }

        public T rateGrowth(double val) {
            rateGrowth = val;
            return self();
        }

        public T numberOwned(int val) {
            numberOwned = val;
            return self();
        }

        abstract GeneratorBody build();

        // Subclasses must override this method to return "this"
        protected abstract T self();
    }

    public int getNumberOwned() {
        return numberOwned;
    }

    public void setNumberOwned(int numberOwned) {
        this.numberOwned = numberOwned;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    @Override
    public String toString() {
        return "Generator: " + name + " level: " + level + " number owned: " + numberOwned;
    }
}
