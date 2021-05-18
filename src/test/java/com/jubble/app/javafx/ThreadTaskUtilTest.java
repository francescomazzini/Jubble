package com.jubble.app.javafx;

import com.jubble.app.javafx.tasks.ConcreteTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class ThreadTaskUtilTest {
    ThreadTaskUtil threads = new ThreadTaskUtil();
    ConcreteTask task = new ConcreteTask();

    @BeforeEach
    void setup() {
        threads.add(task.getName(), new Thread());
    }

    @Test
    public void threadAddShouldBeUnique() {
        threads.add(task.getName(), new Thread());
        threads.add(task.getName(), new Thread());
        threads.add(task.getName(), new Thread());
        assertEquals(1, threads.getThreadNumber());
        threads.getThread(task.getName()).interrupt();
        assertFalse(threads.getThread(task.getName()).isAlive());
    }

    @Test
    public void removedThreadShouldNotBeAlive() {
        threads.getThread(task.getName()).start();
        assertTrue(threads.getThread(task.getName()).isAlive());
        Thread removed = threads.remove(task.getName());
        assertFalse(removed.isAlive());
    }
}
