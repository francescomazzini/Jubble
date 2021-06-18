package com.jubble.app.core.threads;

import static com.google.common.truth.Truth.assertThat;

import com.jubble.app.core.GameActions;
import com.jubble.app.core.Settings;
import com.jubble.app.core.resources.Currency;
import com.jubble.app.core.resources.generator.Generator;
import java.util.List;
import java.util.Timer;
import org.junit.jupiter.api.*;

public class ThreadRunnerTest {

  Timer VALUES_TIMER;
  static List<Generator> generatorList;
  static Currency balance;
  final int timerRefreshRate = 10;
  final int timerDelay = 0;

  @BeforeAll
  static void setUp() {
    generatorList = Settings.getGeneratorList();
  }

  /*
  It is 10ms while actually should be 1000 because the tests must run fast!
   */
  @RepeatedTest(2)
  @Test
  @DisplayName("Timer Should Update Balance Value Every Tot Set ms")
  public void timerShouldUpdateBalanceEveryTotSeconds() throws InterruptedException {

    VALUES_TIMER = new Timer();
    balance = Settings.getCurrencyList().get(0);
    VALUES_TIMER.schedule(new GameValuesThread(), timerDelay, timerRefreshRate);

    double initialValue = balance.getOwned();

    Thread.sleep(timerRefreshRate * 2);

    if (GameActions.getSumTotalProductionGenerators() != 0)
      assertThat(balance.getOwned())
          .isGreaterThan(initialValue + GameActions.getSumTotalProductionGenerators());
    else
      assertThat(balance.getOwned())
          .isEqualTo(initialValue + GameActions.getSumTotalProductionGenerators());

    VALUES_TIMER.cancel();
    VALUES_TIMER.purge();
  }
}
