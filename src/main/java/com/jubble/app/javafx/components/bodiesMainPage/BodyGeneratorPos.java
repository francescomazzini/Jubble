package com.jubble.app.javafx.components.bodiesMainPage;

public enum BodyGeneratorPos {
  ROW_MAX(2),
  COLUMN_MAX(3),
  PAGE_MAX(6);

  private final int value;

  public int value() {
    return value;
  }

  BodyGeneratorPos(int value) {
    this.value = value;
  }
}
