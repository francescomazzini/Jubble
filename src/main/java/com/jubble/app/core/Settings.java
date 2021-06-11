package com.jubble.app.core;

import com.jubble.app.core.components.Currency;
import com.jubble.app.core.components.generator.Generator;

import java.util.List;

public class Settings {

  private static List<Currency> currencies = List.of(new Currency("Energy"));

  private static final List<Generator> GENERATORS=
          List.of(
                  new Generator.Builder().name("Stellar Panel").level(1).costBase(3.738).productionBase(1.67).rateGrowth(1.07).build(),
                  new Generator.Builder().name("Electron Absorber").level(2).costBase(60).productionBase(20).rateGrowth(1.15).build(),
                  new Generator.Builder().name("Nucleus Extractor").level(3).costBase(720).productionBase(90).rateGrowth(1.14).build(),
                  new Generator.Builder().name("Hydrogenator").level(4).costBase(8640).productionBase(360).rateGrowth(1.13).build(),
                  new Generator.Builder().name("Dyson Sphere").level(5).costBase(103680).productionBase(2160).rateGrowth(1.12).build(),
                  new Generator.Builder().name("Black Hole Reverser").level(6).costBase(1244160.0).productionBase(6480).rateGrowth(1.11).build(),
                  new Generator.Builder().name("Planet Destroyer").level(7).costBase(14929920.0).productionBase(19440).rateGrowth(1.10).build(),
                  new Generator.Builder().name("Galaxyneus").level(8).costBase(179159040.0).productionBase(58320).rateGrowth(1.09).build(),
                  new Generator.Builder().name("Spacevoker").level(9).costBase(2149908480.0).productionBase(174960).rateGrowth(1.08).build(),
                  new Generator.Builder().name("Multiverse NMX").level(10).costBase(25798901760.0).productionBase(804816).rateGrowth(1.07).build());


  public static List<Currency> getCurrencies() {
    return currencies;
  }

  public static List<Generator> getGenerators() { return GENERATORS; }

}
