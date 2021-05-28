package com.jubble.app;

import static com.google.common.truth.Truth.assertThat;

import com.jubble.app.components.Currency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CurrencyTest {
  Currency currency = new Currency("TEST");

  @Test
  @DisplayName("Currency should be zero at beginning")
  public void shouldBeZeroAtBeginning() {
    assertThat(currency.getOwned()).isZero();
  }

  @Test
  @DisplayName("Currency should be saved")
  public void shouldSaveCurrency() {
    final double N = 100.35;
    currency.setOwned(N);
    assertThat(currency.getOwned()).isEqualTo(N);
  }
}
