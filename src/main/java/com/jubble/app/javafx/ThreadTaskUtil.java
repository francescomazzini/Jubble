package com.jubble.app.javafx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wrapper for a map of thread where each thread is linked to the
 * name of its specific task.
 * */
public class ThreadTaskUtil {
    /**
     * Contains threads of each task.
     * */
    private final Map<String, Thread> taskThreads;

    ThreadTaskUtil() {
        taskThreads = new HashMap<>();
    }

    /**
     * Add thread to the map.
     * @param threadName name of the thread.
     * @param thread
     * */
    public void add(String threadName, Thread thread) {
        taskThreads.put(threadName, thread);
    }

    /**
     * Stops and remove a thread in the map.
     * @param threadName name of the thread to remove.
     */
    public Thread remove(String threadName) {
        getThread(threadName).interrupt();
        return taskThreads.remove(threadName);
    }

    /**
     * Return number of threads in the Map taskThreads.
     * @return number of threads
     * */
    public int getThreadNumber() {
        return taskThreads.size();
    }

    /**
     * Return a thread in the map given the name of the linked task.
     * @param threadName name of the thread to retrieve.
     * @return thread linked to the map.
     * */
    public Thread getThread(String threadName) {
        return taskThreads.get(threadName);
    }
}
