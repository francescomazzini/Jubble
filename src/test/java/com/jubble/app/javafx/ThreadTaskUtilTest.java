package com.jubble.app.javafx;

import static com.google.common.truth.Truth.assertThat;

import com.jubble.app.javafx.tasks.ConcreteTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ThreadTaskUtilTest {
  ThreadTaskUtil threads = new ThreadTaskUtil();
  ConcreteTask task = new ConcreteTask();

  @BeforeEach
  void setup() {
    threads.create(task);
  }

  @Test
  public void threadCreateShouldBeUnique() {
    threads.create(task);
    threads.create(task);
    threads.create(task);

    assertThat(threads.getThreadNumber()).isEqualTo(1);

    threads.getThread(task.getName()).interrupt();
    assertThat(threads.getThread(task.getName()).isAlive()).isFalse();
  }

  @Test
  public void removedThreadShouldNotBeAlive() throws InterruptedException {
    threads.getThread(task.getName()).start();

    assertThat(threads.getThread(task.getName()).isAlive()).isTrue();

    Thread removed = threads.remove(task.getName());

    assertThat(removed).isNotNull();

    assertThat(removed.isAlive()).isFalse();
  }
}
