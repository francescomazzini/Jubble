package com.jubble.app.telegram;

import com.jubble.app.ThreadRunner;
import com.jubble.app.utils.GameProgressHandler;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class MainTelegram {

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
