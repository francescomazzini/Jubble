package com.jubble.app.components.generator;

/**
 * Represents a generator in the game.
 * Implements the builder pattern and can be instantiated only providing a builder.
 */
public class Generator {
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

    private Generator(Builder builder) {
        name = builder.name;
        description = builder.description;
        level = builder.level;
        costBase = builder.costBase;
        productionBase = builder.productionBase;
        rateGrowth = builder.rateGrowth;
        numberOwned = builder.numberOwned;
    }

    public static class Builder {
        // Optional parameters - initialized to default values.
        private String name = "";
        private String description = "";
        private int level = 0;
        private double costBase = 0d;
        private double productionBase = 0d;
        private double rateGrowth = 0d;
        private int numberOwned = DEFAULT_NUMBER_OWNED_GENERATORS;

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder level(int val) {
            level = val;
            return this;
        }

        public Builder costBase(double val) {
            costBase = val;
            return this;
        }

        public Builder productionBase(double val) {
            productionBase = val;
            return this;
        }

        public Builder rateGrowth(double val) {
            rateGrowth = val;
            return this;
        }

        public Builder numberOwned(int val) {
            numberOwned = val;
            return this;
        }

        public Generator build() {
            return new Generator(this);
        }
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
        return costBase * Math.pow(rateGrowth, numberOwned);
    }

    public double getProduction() {
        return (productionBase * numberOwned) * level;
    }

    public double getProductionBase() {
        return productionBase;
    }

    public String getName() {
        return name;
    }

    /**
     * Tells if numberOwned is more than default value 0.
     *
     * @return true if numberOwned is different than default.
     */
    public boolean isMoreThanZeroOwned() {
        return numberOwned != DEFAULT_NUMBER_OWNED_GENERATORS;
    }

    @Override
    public String toString() {
        return "Generator: " + name + " level: " + level + " number owned: " + numberOwned;
    }
}
