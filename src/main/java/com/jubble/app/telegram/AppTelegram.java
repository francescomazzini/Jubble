package com.jubble.app.telegram;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class AppTelegram {

    /**
     * Main methods create a instance of the bot and run it following the telegram
     * API and use gameOn as variable in the game to know if the user has
     * already clicked begin or not.
     *
     * @param args
     */

    public static void main(String[] args) {

        JubbleBot jubbleBot = null;

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            jubbleBot = new JubbleBot();
            telegramBotsApi.registerBot(jubbleBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        if(jubbleBot != null)
            jubbleBot.setGameOn(false);

    }

}
