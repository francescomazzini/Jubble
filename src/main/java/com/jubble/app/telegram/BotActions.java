package com.jubble.app.telegram;

import com.jubble.app.core.GameActions;
import com.jubble.app.core.Settings;
import com.jubble.app.core.resources.generator.Generator;
import com.jubble.app.core.threads.ThreadRunner;
import com.jubble.app.telegram.elements.TelegramMessage;
import com.jubble.app.telegram.elements.TypeMessages;

public class BotActions {
  private TelegramMessage message;

  BotActions(TelegramMessage message) {
    this.message = message;
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
