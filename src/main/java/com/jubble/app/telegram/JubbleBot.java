package com.jubble.app.telegram;

import com.jubble.app.core.threads.ThreadRunner;
import com.jubble.app.core.components.generator.Generator;
import com.jubble.app.core.utils.GameProgress;
import com.jubble.app.core.utils.GameStarterUtil;
import com.jubble.app.telegram.elements.TelegramMessage;
import com.jubble.app.telegram.elements.TypeMessages;
import com.jubble.app.core.utils.GameActions;
import com.jubble.app.core.utils.GameProgressHandler;
import com.jubble.app.core.Settings;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.Map;

public class JubbleBot extends TelegramLongPollingBot  {

    /**
     * listMessages map is a Map that for each type of TelegramMessage (already written in TypeMessages) has
     * the callBackQuery as key. In this way only one TelegramMessage correspond to a callBackQuery.
     */
    private final Map<String, TelegramMessage> listMessages = TypeMessages.listOfMessages;
    private boolean isGameOn;

    public void setGameOn(boolean gameOn) { isGameOn = gameOn; }

    @Override
    public String getBotUsername() {
        return "jubble_bot";
    }

    @Override
    public String getBotToken() {
        return "1711656042:AAFPvyLcWPSeHwpq7qM-kodBXCYhIKwfKWo";
    }

    /**
     * Each time that the bot receives an update, it analyzes the command receive. It could be a normal
     * command like "/start" or a callBackQuery like "shop". They are treated in the same way
     * so it just put it in a string. Then it search in the map the correct TelegramMessage to use
     * it to answer to the command given.
     *
     * There can be cases where the command is not found and it could be because the commands to buy generators
     * are all wrapped under "gen" command and then followed by the number of the generator they refer to.
     * Because of this there is an if which checks if this is the case. If it is not even the command obviously is
     * not run
     *
     * @param update is the object given by API when the user interacts with the bot
     */
    @Override
    public void onUpdateReceived(Update update) {

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

            actionPerformer(command, telegramMessage);

            telegramMessage.setChatId(update);

            try {
                execute(telegramMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * actionPerformer runs snippet of code modifying TelegramMessage depending on the command
     * inserted. This is needed because some information are generated or executed at run time
     *
     * @param action that is the command given by the update
     * @param tlMsg is the TelegramMessage which correspond to the key command in the Map
     *
     */
    private void actionPerformer (String action, TelegramMessage tlMsg) {

        if(action.equals("begin") && !isGameOn) {
            tlMsg.setContent("The *game* is running.\n" + tlMsg.getContent());
            ThreadRunner.run();
            isGameOn = true;
        }

        if(action.equals("stop") && isGameOn) {
            ThreadRunner.stop();
            isGameOn = false;
        }

        if(action.equals("status"))
            tlMsg.setText(tlMsg.getContent() + TypeMessages.generateStatusContent());

        if(action.equals("shop")) {
            tlMsg.setText(tlMsg.getContent() + TypeMessages.generateShopContent());

            tlMsg.setInlineButtons(TypeMessages.generateShopButtons(), 5);

        }

        if(action.startsWith("gen")) {

            int numGenerator = Integer.parseInt(action.substring(3));
            Generator gen = Settings.getGenerators().get(numGenerator);
            boolean isBought = GameActions.buyGenerator(gen);

            if(isBought)
                tlMsg.setText(tlMsg.getContent() + " successfully bought " + gen.getName() + " ✅ ");
            else
                tlMsg.setText(tlMsg.getContent() + " not enough money" + " \uD83D\uDCDB ");

        }

    }

}
