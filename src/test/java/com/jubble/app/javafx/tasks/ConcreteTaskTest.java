package com.jubble.app.javafx.tasks;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConcreteTaskTest {
  AbstractGameTask task = new BalanceTask();

  @DisplayName("Should display task name")
  @Test
  public void shouldDisplayCorrectName() {
    assertEquals("BalanceTask", task.getName());
  }
}
