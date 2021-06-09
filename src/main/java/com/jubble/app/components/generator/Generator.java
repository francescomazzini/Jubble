package com.jubble.app.components.generator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Represents a generator in the game.
 * Implements the builder pattern and can be instantiated only providing a builder.
 */
@JsonDeserialize(builder = Generator.Builder.class)
public class Generator extends GeneratorBody {

    private Generator(Builder builder) {
        super(builder);
    }

    @JsonPOJOBuilder(withPrefix = "")
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

    @JsonIgnore
    public int getNumberOwned() {
        return super.getNumberOwned();
    }

    public void setNumberOwned(int numberOwned) {
        IllegalOperationException.checkIfNumberOwnedIsDefault(super.getNumberOwned());
        super.setNumberOwned(super.getNumberOwned());
    }

    @JsonIgnore
    public double getNextCost() {
        return super.getCostBase() * Math.pow(super.getRateGrowth(), super.getNumberOwned());
    }

    @JsonIgnore
    public double getProduction() {
        return (super.getProductionBase() * super.getNumberOwned()) * super.getLevel();
    }

    @JsonIgnore
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
    @JsonIgnore
    public boolean isMoreThanZeroOwned() {
        return getNumberOwned() != DEFAULT_NUMBER_OWNED_GENERATORS;
    }

}
