package com.jubble.app.javafx.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class ConcreteTaskTest {
    AbstractTask task = new ConcreteTask();
    @Test
    public void shouldDisplayCorrectName() {
        assertEquals("ConcreteTask", task.getName());
    }
}
