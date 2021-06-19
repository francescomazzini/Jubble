package com.jubble.app.telegram.messages;

/** Defines common position constants of a message. */
public enum MessagePos {
  /** Number of Max Inline Buttons per Row in the message (THE MAX LIMIT OF TELEGRAM IS 8) */
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
