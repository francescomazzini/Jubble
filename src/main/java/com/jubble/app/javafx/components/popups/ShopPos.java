package com.jubble.app.javafx.components.popups;

/** Defines the position in the shop. */
public enum ShopPos {
  /**Max Number of Generators per Row in the shop*/
  ROW_GENERATOR_MAX(3);

  private final int value;

  /**
   * Return value set in enum.
   *
   * @return value.
   */
  public int value() {
    return value;
  }

  ShopPos(int value) {
    this.value = value;
  }
}
