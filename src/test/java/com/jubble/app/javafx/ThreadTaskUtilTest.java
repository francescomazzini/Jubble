package com.jubble.app.javafx;

import static com.google.common.truth.Truth.assertThat;

import com.jubble.app.javafx.tasks.BalanceTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ThreadTaskUtilTest {
  BalanceTask task = new BalanceTask();

  @BeforeEach
  void setup() {
    ThreadTaskUtil.create(task);
  }

  @DisplayName("Should create task univocally")
  @Test
  public void threadCreateShouldBeUnique() {
    ThreadTaskUtil.create(task);
    ThreadTaskUtil.create(task);
    ThreadTaskUtil.create(task);

    assertThat(ThreadTaskUtil.getThreadNumber()).isEqualTo(1);

    ThreadTaskUtil.getThread(task.getName()).interrupt();
    assertThat(ThreadTaskUtil.getThread(task.getName()).isAlive()).isFalse();
  }

  @DisplayName("Removed thread should not be alive")
  @Test
  public void removedThreadShouldNotBeAlive() throws InterruptedException {
    ThreadTaskUtil.getThread(task.getName()).start();

    assertThat(ThreadTaskUtil.getThread(task.getName()).isAlive()).isTrue();

    Thread removed = ThreadTaskUtil.remove(task.getName());

    assertThat(removed).isNotNull();

    assertThat(removed.isAlive()).isFalse();
  }
}
