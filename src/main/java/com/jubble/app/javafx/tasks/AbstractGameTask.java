package com.jubble.app.javafx.tasks;

import javafx.concurrent.Task;

/**
 * Abstract definition of a game task.
 */
public abstract class AbstractGameTask extends Task<Void> {
  /**
   * Defines the amount of time in ms, of which every GUI element attached
   * to the Task should wait before refreshing values.
   */
  protected static final int REFRESH_INTERVAL = 500;
  /**
   * Returns name of a Task.
   *
   * @return simpleName of the class.
   */
  public String getName() {
    return this.getClass().getSimpleName();
  }
}
