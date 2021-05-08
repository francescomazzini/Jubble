package com.jubble.app.classes;

import java.util.ArrayList;
import java.util.List;

public class Settings {

    private static List<Generator> generators = List.of(
            new Generator("Stellar Panel", "",4, 1.67),
            new Generator("Electron Absorber", "",0, 0),
            new Generator("Nucleus Extractor", "",0,0),
            new Generator("Hydrogenator", "",0, 0),
            new Generator("Dyson Sphere", "",0, 0),
            new Generator("Black Hole Reverser", "",0, 0));

    private static List<Currency> currencies = List.of(
            new Currency("Energy"));

    public static List<Generator> getGenerators() {
        return generators;
    }

    public static List<Currency> getCurrencies() { return currencies;}



}
