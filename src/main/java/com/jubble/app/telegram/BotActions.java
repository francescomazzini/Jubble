package com.jubble.app.telegram;

import com.jubble.app.core.GameActions;
import com.jubble.app.core.Settings;
import com.jubble.app.core.resources.generator.Generator;
import com.jubble.app.core.threads.ThreadRunner;
import com.jubble.app.telegram.elements.MessageContent;
import com.jubble.app.telegram.elements.TelegramMessage;
import com.jubble.app.telegram.elements.TypeMessages;

public final class BotActions {
  private TelegramMessage message;
  private boolean isGameOn;

  BotActions() {
    isGameOn = false;
  }

  public void setGameOn(boolean gameOn) {
    isGameOn = gameOn;
  }

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
      } else if (action.startsWith(MessageContent.CHECK_BALANCE.action())) {
        openBalance(action);
      }
    }
  }

  public void start() {
    message.setContent("The *game* is running.\n" + message.getContent());
    ThreadRunner.run();
  }

  public void stop() {
    ThreadRunner.stop();
  }

  public void openStatus() {
    message.setText(message.getContent() + TypeMessages.generateStatusContent());
  }

  public void openShop() {
    message.setText(message.getContent() + TypeMessages.generateShopContent());
    message.setInlineButtons(TypeMessages.generateShopButtons());
  }

  public void openBalance(String action) {

    int numGenerator = Integer.parseInt(action.substring(3));
    Generator gen = Settings.getGeneratorList().get(numGenerator);
    boolean isBought = GameActions.buyGenerator(gen);

    if (isBought)
      message.setText(message.getContent() + " successfully bought " + gen.getName() + " ✅ ");
    else message.setText(message.getContent() + " not enough money" + " \uD83D\uDCDB ");
  }
}
