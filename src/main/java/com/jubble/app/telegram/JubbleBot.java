package com.jubble.app.telegram;

import com.jubble.app.ThreadRunner;
import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.components.generator.GeneratorsSingleton;
import com.jubble.app.telegram.elements.TelegramMessage;
import com.jubble.app.telegram.elements.TypeMessages;
import com.jubble.app.utils.GameProgressHandler;
import com.jubble.app.utils.NumberNames;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JubbleBot extends TelegramLongPollingBot {

    private final Map<String, TelegramMessage> listMessages = TypeMessages.listOfMessages;
    private boolean isGameOn;

    public boolean isGameOn() { return isGameOn; }

    public void setGameOn(boolean gameOn) { isGameOn = gameOn; }

    @Override
    public String getBotUsername() {
        return "jubble_bot";
    }

    @Override
    public String getBotToken() {
        return "1711656042:AAFPvyLcWPSeHwpq7qM-kodBXCYhIKwfKWo";
    }

    @Override
    public void onUpdateReceived(Update update) {

        String stringMessage = "";
        SendMessage message = new SendMessage();
        String command;


        if(update.hasCallbackQuery())
            command = update.getCallbackQuery().getData();
        else
            command = update.getMessage().getText();

        TelegramMessage telegramMessage = listMessages.get(command);

        if(telegramMessage != null) {

            actionPerformer(command, telegramMessage);

            telegramMessage.setChatId(update);

            try {
                execute(telegramMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    private void actionPerformer (String action, TelegramMessage tlMsg) {

        if(action.equals("begin") && !isGameOn) {
            tlMsg.setContent("The *game* is running.\n" + tlMsg.getContent());
            GameProgressHandler.loadGame();
            ThreadRunner.run();
            isGameOn = true;
        }

        if(action.equals("stop") && isGameOn) {
            ThreadRunner.stop();
            isGameOn = false;
        }

        if(action.equals("status")) {
            tlMsg.setText(tlMsg.getContent() + TypeMessages.generateStatus());
        }

    }

    /**
     * The commands available are:
     * begin - starts the game
     * stop - stops the game
     * status - gives you the status of your game stats
     * shop - open the shop
     */
}
