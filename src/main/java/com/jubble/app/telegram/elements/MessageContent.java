package com.jubble.app.telegram.elements;

public enum MessageContent {
  WELCOME(
      "Hi! Welcome to *Jubble* \uD83D\uDC4B.\n\nPress the *button* below to start the game! \uD83D\uDC7D"),
  CHOSE_OPTIONS(
      "You can choose *one* of the *options* below to interact with the game \uD83D\uDC47"),
  STOP_GAME(
      "The *game* has stopped. \uD83D\uDCDB\n\nThank you for playing. Hope to see you soon! ✌️"),
  STATUS("*STATUS*: "),
  OPEN_SHOP("*SHOP*: "),
  CHECK_BALANCE("You have ");

  private String message;

  public String getMessage() {
    return message;
  }

  MessageContent(String message) {
    this.message = message;
  }
}
