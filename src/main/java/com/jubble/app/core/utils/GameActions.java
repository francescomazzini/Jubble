package com.jubble.app.core.utils;

import com.jubble.app.core.Settings;
import com.jubble.app.core.components.Balance;
import com.jubble.app.core.components.generator.Generator;
import com.jubble.app.core.components.generator.IllegalOperationException;
import java.util.List;
import java.util.stream.Collectors;

public class GameActions {

  public static boolean buyGenerator(Generator generator) {

    if (Balance.getPrimary() >= generator.getNextCost()) {
      Balance.addPrimary(generator.getNextCost() * -1);
      generator.incrementNumberOwned();

      return true;
    }

    return false;
  }

  public static double getTotalGeneratorsSum() {
    return Settings.getGenerators().stream().mapToDouble(Generator::getProduction).sum();
  }

  public static List<Integer> getListOfGeneratorsNumberOwned() {
    return Settings.getGenerators().stream()
        .map(Generator::getNumberOwned)
        .collect(Collectors.toList());
  }

  /** Gift initial generator. Used in case the default file is not found. */
  public static void giftInitialAmount() {
    final int FIRST = 0;
    if (Settings.getGenerators().get(FIRST).isMoreThanZeroOwned())
      throw new IllegalOperationException();

    Settings.getGenerators().get(FIRST).incrementNumberOwned();
  }
}
