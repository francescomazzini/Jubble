package com.jubble.app.javafx.tasks;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class ConcreteTaskTest {
  AbstractTask task = new ConcreteTask();

  @Test
  public void shouldDisplayCorrectName() {
    assertEquals("ConcreteTask", task.getName());
  }
}
