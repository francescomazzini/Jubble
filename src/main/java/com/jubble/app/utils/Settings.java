package com.jubble.app.utils;

import com.jubble.app.components.Currency;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.components.generator.GeneratorID;
import com.jubble.app.components.generator.GeneratorValues;
import java.util.List;

public class Settings {

  private static List<Generator> generators =
      List.of(
          new Generator(
              new GeneratorID("Stellar Panel", ""), new GeneratorValues(3.738, 1.67, 1.07)),
          new Generator(
              new GeneratorID("Electron Absorber", ""), new GeneratorValues(60, 20, 1.15)),
          new Generator(
              new GeneratorID("Nucleus Extractor", ""), new GeneratorValues(720, 90, 1.14)),
          new Generator(new GeneratorID("Hydrogenator", ""), new GeneratorValues(8640, 360, 1.13)),
          new Generator(
              new GeneratorID("Dyson Sphere", ""), new GeneratorValues(103680, 2160, 1.12)),
          new Generator(
              new GeneratorID("Black Hole Reverser", ""),
              new GeneratorValues(1244160, 6480, 1.11)));

  private static List<Currency> currencies = List.of(new Currency("Energy"));

  public static List<Generator> getGenerators() {
    return generators;
  }

  public static List<Currency> getCurrencies() {
    return currencies;
  }
}
