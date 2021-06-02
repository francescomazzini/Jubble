package com.jubble.app.javafx;

import com.jubble.app.javafx.tasks.AbstractTask;
import java.util.HashMap;
import java.util.Map;

/** Wrapper for a map of thread where each thread is linked to the name of its specific task. */
public class ThreadTaskUtil {
  /** Contains threads of each task. */
  private static final Map<String, Thread> taskThreads  = new HashMap<>();

  /**
   * Create thread from given Task
   *
   * @param task
   */
  public static void create(AbstractTask task) {
    Thread thread = new Thread(task);
    thread.setName(task.getName());
    taskThreads.put(task.getName(), thread);
  }

  /**
   * Create thread from given Task
   * and start the task with deamonity
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
    return taskThreads.remove(threadName);
  }

  /**
   * Return number of threads in the Map taskThreads.
   *
   * @return number of threads
   */
  public static int getThreadNumber() {
    return taskThreads.size();
  }

  /**
   * Return a thread in the map given the name of the linked task.
   *
   * @param threadName name of the thread to retrieve.
   * @return thread linked to the map.
   */
  public static Thread getThread(String threadName) {
    return taskThreads.get(threadName);
  }


}
