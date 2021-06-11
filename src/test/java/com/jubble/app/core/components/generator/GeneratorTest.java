package com.jubble.app.core.components.generator;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GeneratorTest {
  Generator generator =
          new Generator.Builder().name("Stellar Panel").level(1).costBase(3.738).productionBase(1.67).rateGrowth(1.07).build();

  @Test
  @DisplayName("NumberOwned should be DEFAULT at beginning.")
  public void numberOwnedShouldBeDEFAULTAtBeginning() {
    assertThat(generator.getNumberOwned()).isEqualTo(Generator.DEFAULT_NUMBER_OWNED_GENERATORS);
  }

  @Test
  public void shouldBuildGeneratorWithDefaults() {
    Generator.Builder builder =
            new Generator.Builder().name("TEST");
    assertThat(builder.build().toString()).isEqualTo("Generator: TEST level: 0 number owned: 0");
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
