package com.jubble.app.telegram.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class TelegramMessage extends SendMessage {

  private String content;
  private Map<String, String> inlineButtons;

  public TelegramMessage(String content, Map<String, String> inlineButtons) {
    this.content = content;
    this.inlineButtons = inlineButtons;

    this.setText(content);
    this.setParseMode(ParseMode.MARKDOWN);

    generateMessageButtonsList();
  }

  /** Generates message buttons aligning them in a list. */
  private void generateMessageButtonsList() {

    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

    for (String key : inlineButtons.keySet()) {

      InlineKeyboardButton button = new InlineKeyboardButton();
      button.setText(inlineButtons.get(key));
      button.setCallbackData(key);

      // Set the keyboard to the markup
      rowsInline.add(new ArrayList<>(List.of(button)));
    }

    markupInline.setKeyboard(rowsInline);
    this.setReplyMarkup(markupInline);
  }

  /**
   * Generates Message buttons such that the number of message in a line is 1 <= messageNumber <=
   * MAX_PER_ROW.
   */
  private void generateButtons() {
    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
    List<InlineKeyboardButton> rowInline = null;

    int counter = 0;

    for (String key : inlineButtons.keySet()) {

      if (counter % MessagePos.MAX_BUTTONS_PER_ROW.value() == 0) {
        rowInline = new ArrayList<>();
        rowsInline.add(rowInline);
      }

      InlineKeyboardButton button = new InlineKeyboardButton();
      button.setText(inlineButtons.get(key));
      button.setCallbackData(key);

      // Set the keyboard to the markup
      rowInline.add(button);

      counter++;
    }

    markupInline.setKeyboard(rowsInline);
    this.setReplyMarkup(markupInline);
  }

  /**
   * Set the id of the chat.
   *
   * @param update from chat.
   */
  public void setChatId(Update update) {

    String parameter;

    if (update.hasCallbackQuery())
      parameter = update.getCallbackQuery().getMessage().getChatId() + "";
    else parameter = update.getMessage().getChatId() + "";

    this.setChatId(parameter);
  }

  /**
   * Returns content of a message.
   *
   * @return content of message.
   */
  public String getContent() {
    return content;
  }

  /**
   * Set content of a message.
   *
   * @param content to be set.
   */
  public void setContent(String content) {
    this.content = content;
    this.setText(content);
  }

  /**
   * Sets and generates inline button of a message.
   *
   * @param inlineButtons buttons to be set.
   */
  public void setInlineButtons(Map<String, String> inlineButtons) {
    this.inlineButtons = inlineButtons;

    generateButtons();
  }
}
