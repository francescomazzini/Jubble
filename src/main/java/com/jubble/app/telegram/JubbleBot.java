package com.jubble.app.telegram;

import com.jubble.app.ThreadRunner;
import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.components.generator.GeneratorsSingleton;
import com.jubble.app.utils.GameProgressHandler;
import com.jubble.app.utils.NumberNames;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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

        SendMessage message = new SendMessage();
        String command = update.getMessage().getText();
        Message chatUser = update.getMessage();

        if(command.equals("/start")) {

            message.setText("Hi " + chatUser.getFrom().getUserName() + ", welcome to JUBBLE!" +
                    "\nType \"/begin\" to start the game!" +
                    "\nType \"/sotp\" to stop the game!");

        } else if(command.equals("/begin")) {

            if(!isGameOn) {
                message.setText("Hi " + chatUser.getFrom().getUserName() + ", the game has started");

                GameProgressHandler.loadGame();
                ThreadRunner.run();

                setGameOn(true);
            } else
                message.setText("The game is already running");


        } else if(command.equals("/status")) {

            if(isGameOn) {
                String stringMessage = ("STATUS: " +
                        "\n Balance: " + NumberNames.createString(Balance.getPrimary()) +
                        "\n Current Total Production: " +
                            NumberNames.createString(GeneratorsSingleton.getGenerators()
                                                                        .stream()
                                                                        .mapToDouble(Generator::getProduction)
                                                                        .sum())
                        + " / s" +
                        "\n\n Generators Owned: ");

                for(Generator gen : GeneratorsSingleton.getGenerators()) {
                    stringMessage += "\n   - " + gen.getName() + ": " + gen.getNumberOwned();
                }

                message.setText(stringMessage);

            } else
                message.setText("The game is not running");


        } else if (command.equals("/stop")) {

            if(isGameOn) {
                message.setText("Thank you " + chatUser.getFrom().getUserName() + ", the game stopped");

                ThreadRunner.stop();

                setGameOn(false);
            } else
                message.setText("The game is not running yet");

        }

        message.setChatId(chatUser.getChatId() + "");

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
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
