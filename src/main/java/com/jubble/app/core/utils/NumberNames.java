package com.jubble.app.core.utils;

import java.util.Locale;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/** Util to perform spellout of a double number. */
public class NumberNames {
  private static final String NAMES[] = new String[] {"", "K", "M", "B", "T", "Q"};

  private static final int THOUSAND = 1000;
  private static final NavigableMap<Double, String> MAP;

  static {
    MAP = new TreeMap<>();
    for (int i = 0; i < NAMES.length; i++) {
      MAP.put(Math.pow(THOUSAND, i), NAMES[i]);
    }
  }

  /**
   * Strips decimal part of the number, keeping only the first three significant digits.
   *
   * @param key
   * @param number
   * @return stripped number.
   */
  private static double performApproximation(Double key, double number) {
    double d = key / THOUSAND;
    double m = number / d;
    double f = m / 1000.0f;
    return f;
  }

  /**
   * Perform a spellout of the given number.
   *
   * @param number to spell.
   * @return spelled number, spelled in english and approximated.
   */
  public static String createString(Double number) {
    Map.Entry<Double, String> entry = MAP.floorEntry(Math.abs(number));
    String numberName;
    if (entry == null) {
      numberName = String.format(Locale.US, "%,.2f", number);
    } else {
      double rounded = performApproximation(entry.getKey(), number);
      numberName =
          String.format(Locale.US, "%,.2f", rounded)
              + (!entry.getValue().equals("") ? " " : "")
              + entry.getValue();
    }

    return numberName + " ";
  }
}
