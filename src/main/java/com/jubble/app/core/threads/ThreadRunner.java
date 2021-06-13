package com.jubble.app.core.threads;

import com.jubble.app.core.progress.GameStarterUtil;
import java.util.Timer;

/** Wrapper to manage start/stop of GameValuesThread TimerTask. */
public class ThreadRunner {
  /** Contains each GameValuesThread TimerTask instantiated by run(). */
  private static final Timer valueTimer = new Timer();

  /** Starts instance of the timer GameValuesThread timer. */
  public static void run() {
    final int TIMER_REFRESH_RATE = 1000;
    final int TIMER_DELAY = 0;
    GameStarterUtil.load();
    valueTimer.schedule(new GameValuesThread(), TIMER_DELAY, TIMER_REFRESH_RATE);
    System.out.println("Game thread started");
  }

  private static void stopTimer() {
    // Stop timer tasks.
    valueTimer.cancel();
    // Purge cancelled timers.
    valueTimer.purge();
  }

  /** Stops each timer task and save progress. */
  public static void stop() {
    stopTimer();
    GameStarterUtil.save();
    System.out.println("Game thread stopped");
  }
}
