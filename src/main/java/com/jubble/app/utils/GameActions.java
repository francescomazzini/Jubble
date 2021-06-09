package com.jubble.app.utils;

import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;

public class GameActions {

  public static boolean buyGenerator(Generator generator) {

    if (Balance.getPrimary() > generator.getNextCost()) {
      Balance.setPrimary(Balance.getPrimary() - generator.getNextCost());
      generator.incrementNumberOwned();

      return true;
    }

    return false;
  }
}
