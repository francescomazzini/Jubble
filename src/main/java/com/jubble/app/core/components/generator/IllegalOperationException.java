package com.jubble.app.core.components.generator;

/**
 * The game requires to load saved values from a file, which are set in the lists of generators.
 * This exception is thrown when such values are updated without permission by other methods.
 */
public class IllegalOperationException extends RuntimeException {
  public IllegalOperationException() {
    super("Number of owned generator can be set only at the start of the app.");
  }

  /** @param numberOwned number of owned generator for this generator. */
  static void checkIfNumberOwnedIsDefault(int numberOwned) {
    if (numberOwned != Generator.DEFAULT_NUMBER_OWNED_GENERATORS)
      throw new IllegalOperationException();
  }
}
