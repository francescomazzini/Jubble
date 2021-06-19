package com.jubble.app.javafx.components.bodiesMainPage;

/** Here are defined the constant values for the body of the app. */
public enum BodyGeneratorPos {
  /** Maximum number generators in a row. */
  ROW_MAX(2),
  /** Maximum number of generators in a column. */
  COLUMN_MAX(3),
  /** Maximum number of generators in a page. */
  PAGE_MAX(6);

  private final int value;

  /**
   * Returns a value in this enum.
   *
   * @return value.
   */
  public int value() {
    return value;
  }

  BodyGeneratorPos(int value) {
    this.value = value;
  }
}
