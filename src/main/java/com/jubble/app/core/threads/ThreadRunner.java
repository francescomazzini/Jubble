package com.jubble.app.core.threads;

import com.jubble.app.core.progress.GameStarterUtil;
import java.util.Timer;

/** Wrapper to manage start/stop of GameValuesThread TimerTask. */
public final class ThreadRunner {
  /** Contains each GameValuesThread TimerTask instantiated by run(). */
  private static final Timer VALUES_TIMER = new Timer();

  /** Starts instance of the timer GameValuesThread timer. */
  public static void run() {
    final int timerRefreshRate = 1000;
    final int timerDelay = 0;
    GameStarterUtil.load();
    VALUES_TIMER.schedule(new GameValuesThread(), timerDelay, timerRefreshRate);
  }

  private static void stopTimer() {
    // Stop timer tasks.
    VALUES_TIMER.cancel();
    // Purge cancelled timers.
    VALUES_TIMER.purge();
  }

  /** Stops each timer task and save progress. */
  public static void stop() {
    stopTimer();
    GameStarterUtil.save();
  }
}
