package com.jubble.app.javafx;

import com.jubble.app.javafx.tasks.AbstractGameTask;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** Wrapper for a map of thread where each thread is linked to the name of its specific task. */
public final class ThreadTaskUtil {
  /** Contains threads of each task. */
  private static final Map<String, Thread> STRING_THREAD_MAP = new HashMap<>();

  private ThreadTaskUtil() {}

  /**
   * Create thread from given Task and puts it in a collection.
   *
   * @param task task to be linked to a thread.
   */
  public static void create(final AbstractGameTask task) {
    Objects.requireNonNull(task);
    Thread thread = new Thread(task);
    thread.setName(task.getName());
    STRING_THREAD_MAP.put(task.getName(), thread);
  }

  /**
   * Create thread from given Task and start the task as a daemon.
   *
   * @param task to be built.
   */
  public static void autoBuild(AbstractGameTask task) {
    Objects.requireNonNull(task);
    create(task);
    getThread(task.getName()).setDaemon(true);
    getThread(task.getName()).start();
  }

  /**
   * Stops and remove a thread in the collection.
   *
   * @param threadName name of the thread to remove.
   */
  public static Thread remove(String threadName) throws InterruptedException {
    getThread(threadName).join();
    return STRING_THREAD_MAP.remove(threadName);
  }

  /**
   * Return number of threads in the collection.
   *
   * @return number of threads
   */
  public static int getThreadNumber() {
    return STRING_THREAD_MAP.size();
  }

  /**
   * Return a thread in the collection given the name of the linked task.
   *
   * @param threadName name of the thread to retrieve.
   * @return thread linked to the map.
   */
  public static Thread getThread(final String threadName) {
    Objects.requireNonNull(threadName);
    return STRING_THREAD_MAP.get(threadName);
  }
}
