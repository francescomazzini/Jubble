package com.jubble.app.telegram;

/** Define here bot secrets. */
public enum BotConstants {
  /**Token of the telegram bot gave by the BotFather in telegram */
  BOT_TOKEN("1711656042:AAFPvyLcWPSeHwpq7qM-kodBXCYhIKwfKWo"),
  /**Username of the bot in telegram set in the BotFather settings */
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
