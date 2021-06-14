package com.jubble.app.core;

import com.jubble.app.core.resources.Balance;
import com.jubble.app.core.resources.generator.Generator;
import com.jubble.app.core.resources.generator.IllegalOperationException;
import java.util.List;
import java.util.stream.Collectors;

public class GameActions {

  private GameActions() {}

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
    final int first = 0;
    if (Settings.getGenerators().get(first).isMoreThanZeroOwned())
      throw new IllegalOperationException();

    Settings.getGenerators().get(first).incrementNumberOwned();
  }
}
