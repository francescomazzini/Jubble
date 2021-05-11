package com.jubble.app.components.generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneratorTest {
    Generator generator = new Generator(new GeneratorID("Stellar Panel", ""), new GeneratorValues(3.738, 1.67, 1.07));

    @Test
    @DisplayName("NumberOwned should be zero at beginning.")
    public void numberOwnedShouldBeZeroAtBeginning() {
        assertEquals(generator.getNumberOwned(), 0);
    }

    @Test
    @DisplayName("NumberOwned should increase when called N times.")
    public void shouldIncreaseNumberOwned() {
        final int N = 5;
        for (int i = 0; i < N; i++) {
            generator.incrementNumberOwned();
        }
        assertEquals(generator.getNumberOwned(), N);
    }
}
