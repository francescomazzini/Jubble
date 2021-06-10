package com.jubble.app.utils;

import com.jubble.app.components.Currency;
import com.jubble.app.components.NewSettings;
import com.jubble.app.components.generator.IllegalOperationException;

import java.util.List;

public class Settings {

  private static List<Currency> currencies = List.of(new Currency("Energy"));
  /** Gift initial generator. Used in case the default file is not found. */
  public static void giftInitialAmount() {
    final int FIRST = 0;
    if (NewSettings.getGenerators().get(FIRST).isMoreThanZeroOwned())
      throw new IllegalOperationException();

    NewSettings.getGenerators().get(FIRST).incrementNumberOwned();
  }

  public static List<Currency> getCurrencies() {
    return currencies;
  }
}
