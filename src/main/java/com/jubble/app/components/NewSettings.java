package com.jubble.app.components;

import com.jubble.app.components.generator.Generator;

import java.util.List;

/**
 * Bill Pugh Singleton pattern Implementation. The generators are kept inside a private class that
 * holds a private instance. This ensures that the data can be shared across the application without
 * keeping a global variable.
 */
public class NewSettings {
  private List<Generator> generators;
  private List<Currency> currencies;

  private NewSettings(List<Currency> currencies, List<Generator> generators) {
    this.generators = generators;
    this.currencies = currencies;
  }

  private NewSettings() {
    currencies = List.of(new Currency("Energy"));
    generators = List.of(new Generator.Builder().name("test").build());
  }

  private static class SingletonCage {
    private static NewSettings INSTANCE;
  }

  /**
   * Instantiates and returns an Asset object.
   *
   * @return unique INSTANCE the NewSettings class.
   */
  public static List<Generator> getGenerators() {
    return SingletonCage.INSTANCE.generators;
  }

  public static NewSettings getInstance() {
    return SingletonCage.INSTANCE;
  }

  public List<Currency> getCurrencies() {
    return currencies;
  }
}
