package com.jubble.app;

import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.utils.GameProgressHandler;
import com.jubble.app.utils.Settings;

import java.util.Timer;
import java.util.stream.Collectors;

/**
 * Wrapper to manage start/stop of GameValuesThread TimerTask.
 * */
public class ThreadRunner {
    /**
     * Contains Balance of the player.
     * */
    private static Balance gameBalance;
    /**
     * Contains each GameValuesThread TimerTask instantiated by run().
     * */
    private static final Timer valueTimer = new Timer();

    private static boolean isTimerActive = false;

    /**
     * Starts instance of the timer GameValuesThread timer.
     * */
    public static void run() {
        gameBalance = new Balance();
        valueTimer.schedule(new GameValuesThread(gameBalance), 0, 1000);
        isTimerActive = true;
        System.out.println("Game thread started");
    }

    /**
     * Stops each timer task and save progress.
     * */
    public static void stop() {
        // Stop timer tasks.
        valueTimer.cancel();
        // Purge cancelled timers.
        valueTimer.purge();
        isTimerActive = false;
        // Save game progress.
        GameProgressHandler.saveGame(
                Settings.getGenerators().stream()
                        .mapToInt(Generator::getNumberOwned)
                        .boxed()
                        .collect(Collectors.toList()),
                gameBalance.getPrimary());
        System.out.println("Game thread stopped");
    }

    /**
     * Returns current balance.
     * @return gameBalance balance of the player.
     * */
    public static Balance getGameBalance () {
        return gameBalance;
    }

    /**
     * Returns true if timer is active, false otherwise.
     * @return isTimerActive
     * */
    public static boolean timerStatus() {
        return isTimerActive;
    }
}
