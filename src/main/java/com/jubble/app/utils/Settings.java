package com.jubble.app.utils;

import com.jubble.app.classes.Currency;
import com.jubble.app.classes.Generator;
import java.util.List;

public class Settings {

  private static List<Generator> generators =
      List.of(
          new Generator("Stellar Panel", "", 3.738, 1.67, 1.07),
          new Generator("Electron Absorber", "", 60, 20, 1.15),
          new Generator("Nucleus Extractor", "", 720, 90, 1.14),
          new Generator("Hydrogenator", "", 8640, 360, 1.13),
          new Generator("Dyson Sphere", "", 103680, 2160, 1.12),
          new Generator("Black Hole Reverser", "", 1244160, 6480, 1.11));

  private static List<Currency> currencies = List.of(new Currency("Energy"));

  public static List<Generator> getGenerators() {
    return generators;
  }

  public static List<Currency> getCurrencies() {
    return currencies;
  }
}
