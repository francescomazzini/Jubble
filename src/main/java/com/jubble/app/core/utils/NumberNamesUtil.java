package com.jubble.app.core.utils;

import java.util.*;

/** Util that spells a double number out. */
public final class NumberNamesUtil {
  private static final String[] NAMES = new String[] {"", "K", "M", "B", "T", "Q"};

  private static final int THOUSAND = 1000;
  private static final NavigableMap<Double, String> MAP;

  static {
    MAP = new TreeMap<>();
    for (int i = 0; i < NAMES.length; i++) {
      MAP.put(Math.pow(THOUSAND, i), NAMES[i]);
    }
  }

  private NumberNamesUtil() {}

  /**
   * Strips decimal part of the number, keeping only the first three significant digits.
   *
   * @param key approximated number reference in the map.
   * @param number number to spell.
   * @return stripped number.
   */
  private static double performApproximation(Double key, double number) {
    double d = key / THOUSAND;
    double m = number / d;
    return m / 1000.0f;
  }

  /**
   * Spells the given number out.
   *
   * @param number to spell.
   * @return spelled number, spelled in english and approximated.
   */
  public static String createString(Double number) {
    Objects.requireNonNull(number);
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
