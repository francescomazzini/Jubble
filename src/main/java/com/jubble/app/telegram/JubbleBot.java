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

  /**
   * listMessages map is a Map that for each type of TelegramMessage (already written in
   * TypeMessages) has the callBackQuery as key. In this way only one TelegramMessage correspond to
   * a callBackQuery.
   */
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

  private String getReceivedCommand(Update update) {
    if (update.hasCallbackQuery()) return update.getCallbackQuery().getData();
    else return update.getMessage().getText();
  }

  private TelegramMessage getMenu(String command) {
    return listMessages.get(command);
  }

  private TelegramMessage getSubMenu(String command) {
    String tempCommand = command.substring(0, 3);
    return listMessages.get(tempCommand);
  }

  /**
   * Each time that the bot receives an update, it analyzes the command received. All the commands
   * are defined in {@link com.jubble.app.telegram.elements.MessageContent} Two types of updates
   * exist, that are handled equally by this method: - a command: ex. "/start" - a callBackQuery:
   * ex. "shop". so it just put it in a string. Then it search in the map the correct
   * TelegramMessage to use it to answer to the command given.
   *
   * <p>There can be cases where the command is not found and it could be because the commands to
   * buy generators are all wrapped under "gen" command and then followed by the number of the
   * generator they refer to. Because of this there is an if which checks if this is the case. If it
   * is not even the command obviously is not run
   *
   * @param update is the object given by API when the user interacts with the bot
   */
  @Override
  public void onUpdateReceived(Update update) {
    Objects.requireNonNull(update);
    String command = getReceivedCommand(update);
    TelegramMessage telegramMessage =
        !Objects.isNull(getMenu(command)) ? getMenu(command) : getSubMenu(command);
    tryToExecuteAction(telegramMessage, command, update);
  }

  /**
   * ActionPerformer runs snippet of code modifying TelegramMessage depending on the command
   * inserted. This is needed because some information are generated or executed at run time
   *
   * @param action that is the command given by the update
   * @param tlMsg is the TelegramMessage which correspond to the key command in the Map
   */
  private void actionPerformer(String action, TelegramMessage tlMsg) {
    if (action.equals(MessageContent.CHOSE_OPTIONS.getAction()) && !isGameOn) {
      tlMsg.setContent("The *game* is running.\n" + tlMsg.getContent());
      ThreadRunner.run();
      isGameOn = true;
    }

    if (action.equals(MessageContent.STOP_GAME.getAction()) && isGameOn) {
      ThreadRunner.stop();
      isGameOn = false;
    }

    if (action.equals(MessageContent.STATUS.getAction()))
      tlMsg.setText(tlMsg.getContent() + TypeMessages.generateStatusContent());

    if (action.equals(MessageContent.OPEN_SHOP.getAction())) {
      tlMsg.setText(tlMsg.getContent() + TypeMessages.generateShopContent());
      tlMsg.setInlineButtons(TypeMessages.generateShopButtons(), 5);
    }

    if (action.startsWith(MessageContent.CHECK_BALANCE.getAction())) {
      int numGenerator = Integer.parseInt(action.substring(3));
      Generator gen = Settings.getGeneratorList().get(numGenerator);
      boolean isBought = GameActions.buyGenerator(gen);

      if (isBought)
        tlMsg.setText(tlMsg.getContent() + " successfully bought " + gen.getName() + " ✅ ");
      else tlMsg.setText(tlMsg.getContent() + " not enough money" + " \uD83D\uDCDB ");
    }
  }
}
