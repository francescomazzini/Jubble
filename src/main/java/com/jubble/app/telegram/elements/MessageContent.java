package com.jubble.app.telegram.elements;

public enum MessageContent {
  WELCOME(
      "/start",
      "Hi! Welcome to *Jubble* \uD83D\uDC4B.\n\nPress the *button* below to start the game! \uD83D\uDC7D"),
  CHOSE_OPTIONS(
      "begin",
      "You can choose *one* of the *options* below to interact with the game \uD83D\uDC47"),
  STOP_GAME(
      "stop",
      "The *game* has stopped. \uD83D\uDCDB\n\nThank you for playing. Hope to see you soon! ✌️"),
  STATUS("status", "*STATUS*: "),
  OPEN_SHOP("shop", "*SHOP*: "),
  CHECK_BALANCE("gen", "You have ");

  private final String message;
  private final String action;

  public String getMessage() {
    return message;
  }

  public String getAction() {
    return action;
  }

  MessageContent(String action, String message) {
    this.action = action;
    this.message = message;
  }
}
