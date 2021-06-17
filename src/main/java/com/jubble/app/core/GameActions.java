package com.jubble.app.core;

import com.jubble.app.core.resources.Balance;
import com.jubble.app.core.resources.generator.Generator;
import com.jubble.app.core.resources.generator.IllegalOperationException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/** Util that wraps common actions performed during the game. */
public class GameActions {

  private GameActions() {}

  /**
   * Buys a generator.
   *
   * @param generator type of generator to buy.
   * @return true if generator was bought, false if was no bought.
   */
  public static boolean buyGenerator(Generator generator) {
    Objects.requireNonNull(generator);
    boolean isBought = false;
    if (Balance.getPrimary() >= generator.getNextCost()) {
      Balance.addPrimary(generator.getNextCost() * -1);
      generator.incrementNumberOwned();
      isBought = true;
    }
    return isBought;
  }

  /**
   * Returns sums the production value of each generator.
   *
   * @return total production.
   */
  public static double getSumTotalProductionGenerators() {
    return Settings.getGeneratorList().stream().mapToDouble(Generator::getProduction).sum();
  }

  /**
   * Maps each generator into a list of numberOwned attribute.
   *
   * @return list of number owned generators.
   */
  public static List<Integer> getListOfGeneratorsNumberOwned() {
    return Settings.getGeneratorList().stream()
        .map(Generator::getNumberOwned)
        .collect(Collectors.toList());
  }

  /**
   * Returns the number of generators defined in the Settings class.
   *
   * @return number of generators in the list.
   */
  public static int getNumberOfGenerators() {
    return Settings.getGeneratorList().size();
  }

  /** Gift initial generator. Used in case the default file is not found. */
  public static void giftInitialAmount() {
    final int first = 0;
    if (Settings.getGeneratorList().get(first).isMoreThanZeroOwned())
      throw new IllegalOperationException();

    Settings.getGeneratorList().get(first).incrementNumberOwned();
  }
}
