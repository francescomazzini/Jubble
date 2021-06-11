package com.jubble.app.utils;

import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameActionsTest {

    static Generator genExample;

    @BeforeEach
    void setUp () {

        genExample = new Generator.Builder()
                .name("Stellar Panel")
                .level(1)
                .costBase(3.738)
                .productionBase(1.67)
                .rateGrowth(1.07)
                .build();

    }

    @Test
    @DisplayName("Buy a Generator Should Increase its Number Owned")
    public void buyGeneratorShouldIncreaseNumberOfThatGenerator() {

        Balance.setPrimary(10);
        genExample.setNumberOwned(0);

        GameActions.buyGenerator(genExample);

        assertThat(genExample.getNumberOwned()).isEqualTo(1);

    }

    @Test
    @DisplayName("Buy a Generator Should Increase its Number Owned even if numberOwned > 0")
    public void buyGeneratorShouldIncreaseNumberOfThatGeneratorAlsoIfThereAreAlreadyMoreThan0() {

        genExample.setNumberOwned(5);
        Balance.setPrimary(genExample.getNextCost());

        GameActions.buyGenerator(genExample);

        assertThat(genExample.getNumberOwned()).isEqualTo(6);

    }

    @Test
    @DisplayName("Buy a Generator Without Enough Money Should Do Nothing")
    public void buyGeneratorWithoutEnoughMoneyShouldDoNothing() {

        genExample.setNumberOwned(5);
        Balance.setPrimary(genExample.getNextCost() - 0.000001);

        GameActions.buyGenerator(genExample);

        assertThat(genExample.getNumberOwned()).isEqualTo(5);

    }

    @Test
    @DisplayName("Buy a Generator With Enough Money Should Return True")
    public void buyGeneratorWithEnoughMoneyShouldReturnTrue() {

        Balance.setPrimary(500);
        genExample.setNumberOwned(1);

        assertTrue(GameActions.buyGenerator(genExample));

    }

    @Test
    @DisplayName("Buy a Generator Without Enough Money Should Return False")
    public void buyGeneratorWithoutEnoughMoneyShouldReturnFalse() {

        Balance.setPrimary(0.01);
        genExample.setNumberOwned(1);

        assertFalse(GameActions.buyGenerator(genExample));

    }

}
