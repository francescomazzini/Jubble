package com.jubble.app;

import com.jubble.app.components.Currency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyTest {
    Currency currency = new Currency("TEST");
    @Test
    @DisplayName("Currency should be zero at beginning")
    public void shouldBeZeroAtBeginning() {
        assertEquals(currency.getOwned(), 0);
    }

    @Test
    @DisplayName("Currency should be saved")
    public void shouldSaveCurrency() {
        final double N = 100.35;
        currency.setOwned(N);
        assertEquals(currency.getOwned(), N);
    }
}
