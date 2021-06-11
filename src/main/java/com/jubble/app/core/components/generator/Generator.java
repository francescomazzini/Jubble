package com.jubble.app.core.components.generator;

/**
 * Represents a generator in the game.
 * Implements the builder pattern and can be instantiated only providing a builder.
 */


public class Generator extends GeneratorBody {

    private Generator(Builder builder) {
        super(builder);
    }

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

    public void incrementNumberOwned() {
        super.setNumberOwned(getNumberOwned() + 1);
    }

    public int getNumberOwned() {
        return super.getNumberOwned();
    }

    public void setNumberOwned(int numberOwned) {
        IllegalOperationException.checkIfNumberOwnedIsDefault(getNumberOwned());
        super.setNumberOwned(numberOwned);
    }

    public double getNextCost() {
        return super.getCostBase() * Math.pow(super.getRateGrowth(), super.getNumberOwned());
    }

    public double getProduction() {
        return (super.getProductionBase() * super.getNumberOwned());
    }

    public double getProductionBase() {
        return super.getProductionBase();
    }

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
