package com.jubble.app.components.generator;

public class GeneratorID {
    private String name;
    private String description;
    /**
     * Represent the unique name and description of a generator.
     * @param name name of the generator.
     * @param description description of the generator.
     */
    public GeneratorID(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Generator: " + name + " : " + description;
    }
}
