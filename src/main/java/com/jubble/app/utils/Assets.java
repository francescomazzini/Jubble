package com.jubble.app.utils;

import com.jubble.app.components.generator.Generator;
import com.jubble.app.components.generator.GeneratorID;
import com.jubble.app.components.generator.GeneratorValues;

import java.util.List;

/**
 * Bill Pugh Singleton pattern Implementation.
 * The generators are kept inside a private class that holds a private instance.
 * This ensures that the data can be shared across the application without keeping a
 * global variable.
 */
public class Assets {
    private final List<Generator> GENERATORS;

    private Assets() {
        GENERATORS =
                List.of(
                        new Generator(
                                new GeneratorID("Stellar Panel", "", 1), new GeneratorValues(3.738, 1.67, 1.07)),
                        new Generator(
                                new GeneratorID("Electron Absorber", "", 2), new GeneratorValues(60, 20, 1.15)),
                        new Generator(
                                new GeneratorID("Nucleus Extractor", "", 3), new GeneratorValues(720, 90, 1.14)),
                        new Generator(new GeneratorID("Hydrogenator", "", 4), new GeneratorValues(8640, 360, 1.13)),
                        new Generator(
                                new GeneratorID("Dyson Sphere", "", 5), new GeneratorValues(103680, 2160, 1.12)),
                        new Generator(
                                new GeneratorID("Black Hole Reverser", "", 6),
                                new GeneratorValues(1244160, 6480, 1.11)));

    }

    private static class SingletonCage {
        private static final Assets INSTANCE = new Assets();
    }

    /**
     * Instantiates and returns an Asset object.
     * @return unique INSTANCE the Assets class.
     */
    private static Assets getInstance() {
        return SingletonCage.INSTANCE;
    }
}
