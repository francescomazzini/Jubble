package com.jubble.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ThreadRunnerTest {
    @Test
    @DisplayName("Should start and close Timer correcly")
    void shouldStartAndCloseTimerCorrectly() {
        ThreadRunner.run();
        assertTrue(ThreadRunner.timerStatus());
        ThreadRunner.stop();
        assertFalse(ThreadRunner.timerStatus());
    }
}
