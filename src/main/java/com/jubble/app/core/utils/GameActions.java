package com.jubble.app.core.utils;

import com.jubble.app.core.Settings;
import com.jubble.app.core.components.Balance;
import com.jubble.app.core.components.generator.Generator;
import com.jubble.app.core.components.generator.IllegalOperationException;

public class GameActions {

  public static boolean buyGenerator(Generator generator) {

    if (Balance.getPrimary() >= generator.getNextCost()) {
      Balance.addPrimary(generator.getNextCost() * -1);
      generator.incrementNumberOwned();

      return true;
    }

    return false;
  }

  /** Gift initial generator. Used in case the default file is not found. */
  public static void giftInitialAmount() {
    final int FIRST = 0;
    if (Settings.getGenerators().get(FIRST).isMoreThanZeroOwned())
      throw new IllegalOperationException();

    Settings.getGenerators().get(FIRST).incrementNumberOwned();
  }

}
