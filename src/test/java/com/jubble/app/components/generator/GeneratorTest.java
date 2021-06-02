package com.jubble.app.components.generator;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GeneratorTest {
  Generator generator =
      new Generator(
          new GeneratorID("Stellar Panel", "", 1), new GeneratorValues(3.738, 1.67, 1.07));

  @Test
  @DisplayName("NumberOwned should be DEFAULT at beginning.")
  public void numberOwnedShouldBeDEFAULTAtBeginning() {
    assertThat(generator.getNumberOwned()).isEqualTo(Generator.DEFAULT_NUMBER_OWNED_GENERATORS);
  }

  @Test
  public void shouldNeverUpdateNumberOwnedWhenNotDefault() {
    generator.incrementNumberOwned();
    assertThrows(IllegalOperationException.class, () -> generator.setNumberOwned(44));
  }

  @Test
  void shouldUpdateNumberOwnedWhenDefault() {
    final int N = 100;
    generator.setNumberOwned(N);
    assertThat(generator.getNumberOwned()).isEqualTo(N);
  }

  @Test
  @DisplayName("NumberOwned should increase when called N times.")
  public void shouldIncreaseNumberOwned() {
    final int N = 5;
    for (int i = 0; i < N; i++) {
      generator.incrementNumberOwned();
    }

    assertThat(generator.getNumberOwned()).isEqualTo(N);
  }
}
