package com.jubble.app.telegram;

import com.jubble.app.ThreadRunner;
import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.components.generator.GeneratorsSingleton;
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

import java.util.ArrayList;
import java.util.List;

public class JubbleBot extends TelegramLongPollingBot {

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

        if(update.hasCallbackQuery()) {
            // Set variables

            String call_data = update.getCallbackQuery().getData();

            if (call_data.equals("status_inline")) {

                stringMessage = ("*STATUS*: " +
                        "\n *Balance*: " + NumberNames.createString(Balance.getPrimary()) +
                        "\n *Total Production*: " +
                        NumberNames.createString(GeneratorsSingleton.getGenerators()
                                .stream()
                                .mapToDouble(Generator::getProduction)
                                .sum())
                        + " / s" +
                        "\n\n *Generators Owned*: ");

                for(Generator gen : GeneratorsSingleton.getGenerators()) {
                    stringMessage += "\n   • " + gen.getName() + ": " + gen.getNumberOwned();
                }

            }
        } else {


            String command = update.getMessage().getText();
            Message chatUser = update.getMessage();

            if (command.equals("/start")) {

                stringMessage = "Hi " + chatUser.getFrom().getUserName() + ", welcome to JUBBLE!" +
                        "\nType \"/begin\" to start the game!" +
                        "\nType \"/sotp\" to stop the game!";

            } else if (command.equals("/begin")) {

                if (!isGameOn) {
                    stringMessage = "Hi " + chatUser.getFrom().getUserName() + ", the game has started";

                    GameProgressHandler.loadGame();
                    ThreadRunner.run();

                    createInlineKeyboard(message);

                    setGameOn(true);
                } else
                    stringMessage = "The game is already running";


            } else if (command.equals("/status")) {

                if (isGameOn) {
                    stringMessage = ("*STATUS*: " +
                            "\n *Balance*: " + NumberNames.createString(Balance.getPrimary()) +
                            "\n *Total Production*: " +
                            NumberNames.createString(GeneratorsSingleton.getGenerators()
                                    .stream()
                                    .mapToDouble(Generator::getProduction)
                                    .sum())
                            + " / s" +
                            "\n\n *Generators Owned*: ");

                    for (Generator gen : GeneratorsSingleton.getGenerators()) {
                        stringMessage += "\n   • " + gen.getName() + ": " + gen.getNumberOwned();
                    }


                } else
                    stringMessage = "The game is not running";


            } else if (command.equals("/stop")) {

                if (isGameOn) {
                    stringMessage = "Thank you " + chatUser.getFrom().getUserName() + ", the game stopped";

                    ThreadRunner.stop();

                    setGameOn(false);
                } else
                    stringMessage = "The game is not running yet";

            }
        }


        message.setText(stringMessage);
        message.setParseMode(ParseMode.MARKDOWN);

        if(update.hasCallbackQuery())
            message.setChatId(update.getCallbackQuery().getMessage().getChatId() + "");
        else
            message.setChatId(update.getMessage().getChatId() + "");

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void createInlineKeyboard (SendMessage message) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Status");
        button.setCallbackData("status_inline");

        rowInline.add(button);
        // Set the keyboard to the markup
        rowsInline.add(rowInline);
        // Add it to the message
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
    }


    /**
     * The commands available are:
     * begin - starts the game
     * stop - stops the game
     * status - gives you the status of your game stats
     * shop - open the shop
     */
}
