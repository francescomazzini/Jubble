package com.jubble.app.telegram.elements;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TelegramMessage extends SendMessage {


    private String content;
    private Map<String, String> inlineButtons;

    public TelegramMessage (String content,  Map<String, String> inlineButtons ) {
        this.content = content;
        this.inlineButtons = inlineButtons;

        this.setText(content);
        this.setParseMode(ParseMode.MARKDOWN);

        generateButtons();

    }

    private void generateButtons() {

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();


        for(String key : inlineButtons.keySet()) {

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(inlineButtons.get(key));
            button.setCallbackData(key);

            // Set the keyboard to the markup
            rowsInline.add(new ArrayList<>(List.of(button)));

        }

        markupInline.setKeyboard(rowsInline);
        this.setReplyMarkup(markupInline);
    }

    private void generateButtons(int MAX_PER_ROW) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = null;

        int counter = 0;

        for(String key : inlineButtons.keySet()) {

            if(counter % MAX_PER_ROW == 0) {
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

    public void setChatId (Update update) {

        String parameter = "";

        if(update.hasCallbackQuery())
            parameter = update.getCallbackQuery().getMessage().getChatId() + "";
        else
            parameter = update.getMessage().getChatId() + "";

        this.setChatId(parameter);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.setText(content);
    }

    public Map<String, String> getInlineButtons() {
        return inlineButtons;
    }

    public void setInlineButtons(Map<String, String> inlineButtons) {
        this.inlineButtons = inlineButtons;

        generateButtons();
    }

    public void setInlineButtons(Map<String, String> inlineButtons, int MAX_PER_ROW) {
        this.inlineButtons = inlineButtons;

        generateButtons(MAX_PER_ROW);
    }
}
