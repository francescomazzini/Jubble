package com.jubble.app.telegram.messages;

import java.util.Objects;

/** Message and their contents are defined here. */
public enum MessageContent {
  /** Welcome Message Content that is activable with /start */
  WELCOME(
      "/start",
      "Hi! Welcome to *Jubble* \uD83D\uDC4B.\n\nPress the *button* below to start the game! \uD83D\uDC7D"),
  /** Choose Option Message Content that is activable with begin */
  CHOSE_OPTIONS(
      "begin",
      "You can choose *one* of the *options* below to interact with the game \uD83D\uDC47"),
  /** Stop Game Message Content that is activable with stop */
  STOP_GAME(
      "stop",
      "The *game* has stopped. \uD83D\uDCDB\n\nThank you for playing. Hope to see you soon! ✌️"),
  /** Status Message Content that is activable with status */
  STATUS("status", "*STATUS*: "),
  /** Open Shop Message Content that is activable with shop */
  OPEN_SHOP("shop", "*SHOP*: "),
  /**
   * Buy Generator Message Content that is activable with gen (the actual callBack will have also
   * the number of the generator wanted after "gen")
   */
  BUY_GEN("gen", "You have ");

  private final String message;
  private final String action;

  /**
   * Returns message defined in enum.
   *
   * @return message.
   */
  public String message() {
    return message;
  }

  /**
   * Returns action defined in enum.
   *
   * @return action.
   */
  public String action() {
    return action;
  }

  MessageContent(String action, String message) {
    this.action = Objects.requireNonNull(action);
    this.message = Objects.requireNonNull(message);
  }
}
