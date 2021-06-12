package com.jubble.app.core.components;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CurrencyTest {
  Currency currency = new Currency("TEST");

  @Test
  @DisplayName("Currency Should Be Zero At Beginning")
  public void shouldBeZeroAtBeginning() {
    assertThat(currency.getOwned()).isZero();
  }

  @Test
  @DisplayName("Currency Should Be Set with setOwned()")
  public void shouldSetCurrencyCorrectly() {
    final double N = 100.35;
    currency.setOwned(N);
    assertThat(currency.getOwned()).isEqualTo(N);
  }
}
