package com.jubble.app.javafx;

import com.jubble.app.javafx.tasks.AbstractTask;
import java.util.HashMap;
import java.util.Map;

/** Wrapper for a map of thread where each thread is linked to the name of its specific task. */
public final class ThreadTaskUtil {
  /** Contains threads of each task. */
  private static final Map<String, Thread> STRING_THREAD_MAP = new HashMap<>();

  private ThreadTaskUtil() {}

  /**
   * Create thread from given Task.
   *
   * @param task
   */
  public static void create(AbstractTask task) {
    Thread thread = new Thread(task);
    thread.setName(task.getName());
    STRING_THREAD_MAP.put(task.getName(), thread);
  }

  /**
   * Create thread from given Task and start the task as a daemon.
   *
   * @param task
   */
  public static void autoBuild(AbstractTask task) {
    create(task);
    getThread(task.getName()).setDaemon(true);
    getThread(task.getName()).start();
  }

  /**
   * Stops and remove a thread in the map.
   *
   * @param threadName name of the thread to remove.
   */
  public static Thread remove(String threadName) throws InterruptedException {
    getThread(threadName).join();
    return STRING_THREAD_MAP.remove(threadName);
  }

  /**
   * Return number of threads in the Map taskThreads.
   *
   * @return number of threads
   */
  public static int getThreadNumber() {
    return STRING_THREAD_MAP.size();
  }

  /**
   * Return a thread in the map given the name of the linked task.
   *
   * @param threadName name of the thread to retrieve.
   * @return thread linked to the map.
   */
  public static Thread getThread(String threadName) {
    return STRING_THREAD_MAP.get(threadName);
  }
}
