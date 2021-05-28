package com.jubble.app;

import static com.google.common.truth.Truth.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ThreadRunnerTest {
  @Test
  @DisplayName("Should start and close Timer correcly")
  void shouldStartAndCloseTimerCorrectly() {
    ThreadRunner.run();

    assertThat(ThreadRunner.timerStatus()).isTrue();
    ThreadRunner.stop();
    assertThat(ThreadRunner.timerStatus()).isFalse();
  }
}
