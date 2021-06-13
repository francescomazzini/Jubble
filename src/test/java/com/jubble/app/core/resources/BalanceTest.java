package com.jubble.app.core.components;

import static com.google.common.truth.Truth.assertThat;

import com.jubble.app.core.GameActions;
import com.jubble.app.core.components.generator.Generator;

import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class BalanceTest {

  @Test
  @RepeatedTest(4)
  @DisplayName("Changing Balance Concurrently Should Have Coherent Results")
  public void changeBalanceConcurrentlyShouldHaveCoherentResults() throws InterruptedException {

    Balance.setPrimary(60);
    double startValue = Balance.getPrimary();

    Thread t1 =
        new Thread(
            () -> {
              for (int i = 0; i < 15; i++) {
                Balance.addPrimary(50);
                try {
                  Thread.sleep(10);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            });

    Thread t2 =
        new Thread(
            () -> {
              for (int i = 0; i < 15; i++) {
                Balance.addPrimary(-50);
                try {
                  Thread.sleep(10);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            });

    t1.start();
    t2.start();
    t1.join();
    t2.join();

    assertThat(Balance.getPrimary()).isEqualTo(startValue);
  }

  @Test
  @RepeatedTest(4)
  @DisplayName("Adding Balance Concurrently While Buy Generators Should Have Coherent Results")
  public void addBalanceConcurrentlyWhileBuyGeneratorsShouldHaveCoherentResults()
      throws InterruptedException {

    Generator genExample =
        new Generator.Builder()
            .name("Stellar Panel")
            .level(1)
            .costBase(3.738)
            .productionBase(1.67)
            .rateGrowth(1.07)
            .build();

    Balance.setPrimary(300);
    double startValue = Balance.getPrimary();

    Thread t1 =
        new Thread(
            () -> {
              for (int i = 0; i < 15; i++) {
                Balance.addPrimary(50);
                try {
                  Thread.sleep(10);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            });

    AtomicReference<Double> costTotal = new AtomicReference<>();

    Thread t2 =
        new Thread(
            () -> {
              double costTotalGenerator = 0;
              for (int i = 0; i < 10; i++) {
                costTotalGenerator += genExample.getNextCost();
                GameActions.buyGenerator(genExample);
                try {
                  Thread.sleep(10);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
              costTotal.set(costTotalGenerator);
            });

    t1.start();
    t2.start();
    t1.join();
    t2.join();

    // rounding values
    double actualResult = Math.round(Balance.getPrimary() * 10000) / 10000.0;
    double expectedResult = Math.round((startValue + 50 * 15 - costTotal.get()) * 10000) / 10000.0;

    assertThat(actualResult).isEqualTo(expectedResult);
  }
}
