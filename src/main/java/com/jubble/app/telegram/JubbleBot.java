package com.jubble.app.telegram;

import com.jubble.app.ThreadRunner;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.components.generator.GeneratorsSingleton;
import com.jubble.app.telegram.elements.TelegramMessage;
import com.jubble.app.telegram.elements.TypeMessages;
import com.jubble.app.utils.GameActions;
import com.jubble.app.utils.GameProgressHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

public class JubbleBot extends TelegramLongPollingBot  {

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

        if(telegramMessage == null) {
            String tempCommand = command.substring(0,3);
            telegramMessage = listMessages.get(tempCommand);
        }

        if(telegramMessage != null) {

            try {
                actionPerformer(command, telegramMessage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            telegramMessage.setChatId(update);

            try {
                execute(telegramMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    private void actionPerformer (String action, TelegramMessage tlMsg) throws FileNotFoundException {

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

        if(action.equals("status"))
            tlMsg.setText(tlMsg.getContent() + TypeMessages.generateStatusMessage());

        if(action.equals("shop")) {
            tlMsg.setText(tlMsg.getContent() + TypeMessages.generateShopMessage());

            tlMsg.setInlineButtons(TypeMessages.generateShopButtons(), 5);

        }

        if(action.startsWith("gen")) {

            int numGenerator = Integer.parseInt(action.substring(3));
            Generator gen = GeneratorsSingleton.getGenerators().get(numGenerator);
            boolean isBought = GameActions.buyGenerator(gen);

            if(isBought)
                tlMsg.setText(tlMsg.getContent() + " successfully bought " + gen.getName());
            else
                tlMsg.setText(tlMsg.getContent() + " not enough money");

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
