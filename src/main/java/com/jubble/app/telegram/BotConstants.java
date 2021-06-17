package com.jubble.app.telegram;

/** Define here bot secrets. */
public enum BotConstants {
  BOT_TOKEN("1711656042:AAFPvyLcWPSeHwpq7qM-kodBXCYhIKwfKWo"),
  BOT_USERNAME("jubble_bot");

  private final String value;

  /**
   * Returns value.
   *
   * @return value in the enum.
   */
  public String value() {
    return value;
  }

  BotConstants(String value) {
    this.value = value;
  }
}
