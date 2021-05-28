package com.jubble.app.components.generator;


import static com.google.common.truth.Truth.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GeneratorTest {
  Generator generator =
      new Generator(new GeneratorID("Stellar Panel", ""), new GeneratorValues(3.738, 1.67, 1.07));

  @Test
  @DisplayName("NumberOwned should be zero at beginning.")
  public void numberOwnedShouldBeZeroAtBeginning() {
    assertThat(generator.getNumberOwned()).isEqualTo(0);
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
