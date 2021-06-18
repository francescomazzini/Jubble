package com.jubble.app.telegram;

import com.jubble.app.core.GameActions;
import com.jubble.app.core.Settings;
import com.jubble.app.core.resources.generator.Generator;
import com.jubble.app.core.threads.ThreadRunner;
import com.jubble.app.telegram.messages.MessageContent;
import com.jubble.app.telegram.messages.TelegramMessage;
import com.jubble.app.telegram.messages.TypeMessages;
import java.util.Objects;

/** Defines the possible actions that can be performed by the bot. */
public final class BotActions {
  private TelegramMessage message;
  private boolean isGameOn = false;

  /**
   * Set the Game On or Off, it is used to know in the response of the callback actions if the game
   * has already been activated or not
   *
   * @param isGameOn status to be set
   */
  public void setIsGameOn(final boolean isGameOn) {
    this.isGameOn = isGameOn;
  }

  /**
   * Perform an action.
   *
   * @param action to be executed.
   * @param message received from the chat.
   */
  public void perform(final String action, final TelegramMessage message) {
    this.message = message;

    if (action.equals(MessageContent.CHOSE_OPTIONS.action()) && !isGameOn) {
      start();
      isGameOn = true;
    }
    if (action.equals(MessageContent.STOP_GAME.action()) && isGameOn) {
      stop();
      isGameOn = false;
    } else {
      if (action.equals(MessageContent.STATUS.action())) {
        openStatus();
      } else if (action.equals(MessageContent.OPEN_SHOP.action())) {
        openShop();
      } else if (action.startsWith(MessageContent.BUY_GEN.action())) {
        openBalance(action);
      }
    }
  }

  private void start() {
    message.setContent("The *game* is running.\n" + message.getContent());
    ThreadRunner.run();
  }

  private void stop() {
    ThreadRunner.stop();
  }

  private void openStatus() {
    message.setText(message.getContent() + TypeMessages.generateStatusContent());
  }

  private void openShop() {
    message.setText(message.getContent() + TypeMessages.generateShopContent());
    message.setInlineButtons(TypeMessages.generateShopButtons());
  }

  private void openBalance(final String action) {
    Objects.requireNonNull(action);

    int numGenerator = Integer.parseInt(action.substring(3));
    Generator gen = Settings.getGeneratorList().get(numGenerator);
    boolean isBought = GameActions.buyGenerator(gen);

    if (isBought)
      message.setText(message.getContent() + " successfully bought " + gen.getName() + " ✅ ");
    else message.setText(message.getContent() + " not enough money" + " \uD83D\uDCDB ");
  }
}
