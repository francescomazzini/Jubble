package com.jubble.app.javafx.components.bodiesMainPage;

/** Here are defined the constant values for the body of the app. */
public enum BodyGeneratorPos {
  ROW_MAX(2),
  COLUMN_MAX(3),
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
