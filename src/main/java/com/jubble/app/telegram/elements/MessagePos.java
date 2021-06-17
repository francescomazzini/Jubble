package com.jubble.app.telegram.elements;

/** Defines common position constants of a message. */
public enum MessagePos {
  MAX_BUTTONS_PER_ROW(5);
  private final int value;

  public int value() {
    return value;
  }

  MessagePos(int value) {
    this.value = value;
  }
}
