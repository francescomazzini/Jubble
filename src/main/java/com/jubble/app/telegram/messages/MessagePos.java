package com.jubble.app.telegram.messages;

/** Defines common position constants of a message. */
public enum MessagePos {
  MAX_BUTTONS_PER_ROW(5);
  private final int value;

  /**
   * Returns value defined in enum.
   *
   * @return value.
   */
  public int value() {
    return value;
  }

  MessagePos(int value) {
    this.value = value;
  }
}
