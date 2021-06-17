package com.jubble.app.telegram;

import com.jubble.app.core.GameActions;
import com.jubble.app.core.Settings;
import com.jubble.app.core.resources.generator.Generator;
import com.jubble.app.core.threads.ThreadRunner;
import com.jubble.app.telegram.elements.MessageContent;
import com.jubble.app.telegram.elements.TelegramMessage;
import com.jubble.app.telegram.elements.TypeMessages;
import java.util.Map;
import java.util.Objects;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public final class JubbleBot extends TelegramLongPollingBot {

  /** Maps the command name (callBackQuery) with each TelegramMessage. */
  private final Map<String, TelegramMessage> listMessages = TypeMessages.listOfMessages;

  private boolean isGameOn;

  public void setGameOn(boolean gameOn) {
    isGameOn = gameOn;
  }

  /**
   * Returns the predefined bot username.
   *
   * @return bot username.
   */
  @Override
  public String getBotUsername() {
    return BotConstants.BOT_USERNAME.getValue();
  }

  /**
   * Returns the predefined bot token.
   *
   * @return bot token.
   */
  @Override
  public String getBotToken() {
    return BotConstants.BOT_TOKEN.getValue();
  }

  private void tryToExecuteAction(TelegramMessage telegramMessage, String command, Update update) {
    Objects.requireNonNull(telegramMessage);
    actionPerformer(command, telegramMessage);
    telegramMessage.setChatId(update);
    try {
      execute(telegramMessage);
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }

  private String getCommandFrom(Update update) {
    if (update.hasCallbackQuery()) return update.getCallbackQuery().getData();
    else return update.getMessage().getText();
  }

  private TelegramMessage getMenu(String command) {
    return listMessages.get(command);
  }

  private String extractGeneratorCommand(String command) {
    return command.substring(0, 3);
  }

  private TelegramMessage getSubMenu(String command) {
    String tempCommand = extractGeneratorCommand(command);
    return listMessages.get(tempCommand);
  }

  /**
   * All the commands are defined in {@link com.jubble.app.telegram.elements.MessageContent} Two
   * types of updates exist, that are handled equally by this method: - a command: ex. "/start" - a
   * callBackQuery: ex. "shop". so it just put it in a string.
   *
   * <p>There can be cases where the command is not found and it could be because the commands to
   * buy generators are all wrapped under "gen" command and then followed by the number of the
   * generator they refer to.
   *
   * @param update is the object given by API when the user interacts with the bot.
   */
  @Override
  public void onUpdateReceived(Update update) {
    Objects.requireNonNull(update);
    String command = getCommandFrom(update);
    TelegramMessage message =
        !Objects.isNull(getMenu(command)) ? getMenu(command) : getSubMenu(command);
    tryToExecuteAction(message, command, update);
  }

  /**
   * Runs the method linked to each command.
   *
   * @param action command given by the update.
   * @param message TelegramMessage which correspond to the key command in the Map.
   */
  private void actionPerformer(final String action, final TelegramMessage message) {
    Objects.requireNonNull(action);
    Objects.requireNonNull(message);
    if (action.equals(MessageContent.CHOSE_OPTIONS.getAction()) && !isGameOn) {
      message.setContent("The *game* is running.\n" + message.getContent());
      ThreadRunner.run();
      isGameOn = true;
    }
    if (action.equals(MessageContent.STOP_GAME.getAction()) && isGameOn) {
      ThreadRunner.stop();
      isGameOn = false;
    } else {
      if (action.equals(MessageContent.STATUS.getAction())) {
        message.setText(message.getContent() + TypeMessages.generateStatusContent());
      } else if (action.equals(MessageContent.OPEN_SHOP.getAction())) {
        message.setText(message.getContent() + TypeMessages.generateShopContent());
        message.setInlineButtons(TypeMessages.generateShopButtons());
      } else if (action.startsWith(MessageContent.CHECK_BALANCE.getAction())) {
        int numGenerator = Integer.parseInt(action.substring(3));
        Generator gen = Settings.getGeneratorList().get(numGenerator);
        boolean isBought = GameActions.buyGenerator(gen);

        if (isBought)
          message.setText(message.getContent() + " successfully bought " + gen.getName() + " ✅ ");
        else message.setText(message.getContent() + " not enough money" + " \uD83D\uDCDB ");
      }
    }
  }
}
