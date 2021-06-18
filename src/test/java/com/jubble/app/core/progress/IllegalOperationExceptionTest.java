package com.jubble.app.core.progress;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jubble.app.core.resources.generator.IllegalOperationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IllegalOperationExceptionTest {

  @DisplayName("Should throw when condition is met")
  @Test
  void shouldThrowOnConditionMet() {
    assertThrows(
        IllegalOperationException.class,
        () -> IllegalOperationException.checkIfNumberOwnedIsDefault(1));
  }

  @DisplayName("Should throw with message")
  @Test
  void shouldThrowWithMessage() {
    final String message = "Number of owned generator can be set only at the start of the app.";

    Exception exception = new IllegalOperationException();
    assertThat(exception.getMessage()).isEqualTo(message);
  }
}
