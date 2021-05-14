package com.jubble.app;

import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.utils.GameProgressHandler;
import com.jubble.app.utils.Settings;

import java.util.Timer;
import java.util.stream.Collectors;

public class ThreadRunner {
    private static Balance gameBalance;

    public static void run() {
        gameBalance = new Balance();
        Timer timer = new Timer();
        timer.schedule(new GameValuesThread(gameBalance), 0, 1000);

    }


    public static void stop() {
        Timer timer = new Timer();
        timer.schedule(new GameValuesThread(gameBalance), 0, 1000);
        GameProgressHandler.saveGame(
                Settings.getGenerators().stream()
                        .mapToInt(Generator::getNumberOwned)
                        .boxed()
                        .collect(Collectors.toList()),
                gameBalance.getPrimary());
    }

    public static Balance getGameBalance () {
        return gameBalance;
    }

}
