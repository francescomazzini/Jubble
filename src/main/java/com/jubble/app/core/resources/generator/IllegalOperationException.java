package com.jubble.app.core.resources.generator;

/**
 * The game requires to load saved values from a file, which are set in the lists of generators.
 * This exception is thrown when such values are updated without permission by other methods.
 */
public class IllegalOperationException extends RuntimeException {
  private static final String MESSAGE =
      "Number of owned generator can be set only at the start of the app.";

  /**
   * IllegalOperationException is a RuntimeException that should be run when a method tries to
   * perform a illegal operation that breaks the logic of the game.
   */
  public IllegalOperationException() {
    super(MESSAGE);
  }

  /**
   * Throws an exception if param is different than default.
   *
   * @param numberOwned number of owned generator for this generator.
   * @throws IllegalOperationException if the following condition is met.
   */
  public static void checkIfNumberOwnedIsDefault(int numberOwned) {
    if (numberOwned != Generator.DEFAULT_NUMBER_OWNED_GENERATORS)
      throw new IllegalOperationException();
  }
}
