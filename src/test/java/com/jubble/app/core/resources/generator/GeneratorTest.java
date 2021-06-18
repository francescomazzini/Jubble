package com.jubble.app.core.resources.generator;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GeneratorTest {
  Generator generator =
      new Generator.Builder()
          .name("Stellar Panel")
          .level(1)
          .costBase(3.738)
          .productionBase(1.67)
          .rateGrowth(1.07)
          .build();

  @Test
  @DisplayName("NumberOwned should be DEFAULT at beginning")
  public void numberOwnedShouldBeDEFAULTAtBeginning() {
    assertThat(generator.getNumberOwned()).isEqualTo(Generator.DEFAULT_NUMBER_OWNED_GENERATORS);
  }

  @Test
  @DisplayName("Generator Properties Should Be Default If Not Specified")
  public void shouldBuildGeneratorWithDefaults() {
    Generator.Builder builder = new Generator.Builder().name("TEST");
    assertThat(builder.build().toString()).isEqualTo("Generator: TEST level: 0 number owned: 0");
  }

  @Test
  @DisplayName(
      "Trying To Set Generator Number Owned When Not Default Should Throw IllegalOperationException")
  public void tryToSetGeneratorNumberOwnedWhenNotDefaultShouldThrowIllegalOperationException() {
    generator.incrementNumberOwned();
    assertThrows(IllegalOperationException.class, () -> generator.setNumberOwned(44));
  }

  @Test
  @DisplayName("Set Generator Number Owned When Default Should Work")
  void shouldUpdateNumberOwnedWhenDefault() {
    final int N = 100;
    generator.setNumberOwned(N);
    assertThat(generator.getNumberOwned()).isEqualTo(N);
  }

  @Test
  @DisplayName("NumberOwned Should Increase When Called N Times.")
  public void shouldIncreaseNumberOwned() {
    final int N = 5;
    for (int i = 0; i < N; i++) {
      generator.incrementNumberOwned();
    }

    assertThat(generator.getNumberOwned()).isEqualTo(N);
  }

  @Test
  @DisplayName("Cost of Generator Should Increase Based On Rate Growth")
  public void costShouldIncrementBasedOnRateGrowth() {

    generator.setNumberOwned(3);

    double costNow = generator.getNextCost();

    generator.incrementNumberOwned();

    double actualResult = Math.round(generator.getNextCost() * 10000) / 10000.0;
    double expectedResult = Math.round((costNow * generator.getRateGrowth()) * 10000) / 10000.0;

    assertThat(actualResult).isEqualTo(expectedResult);
  }

  @Test
  @DisplayName("Total Production of a Generator Should Be numberOwned * productionBase")
  public void totalProductionOfAGeneratorShouldBeNumberOwnedTimesProductionBase() {

    generator.setNumberOwned(3);

    double actualResult = Math.round(generator.getProduction() * 10000) / 10000.0;
    double expectedResult = Math.round((generator.getProductionBase() * 3) * 10000) / 10000.0;

    assertThat(actualResult).isEqualTo(expectedResult);
  }

  @Test
  @DisplayName("NumberOwned Should Be Settable At The Beginning Also Through Builder")
  public void numberOwnedShouldBeSettableAtBeginningAlsoThroughBuilder() {

    generator =
            new Generator.Builder()
                    .name("Stellar Panel")
                    .level(1)
                    .description("")
                    .costBase(3.738)
                    .productionBase(1.67)
                    .rateGrowth(1.07)
                    .numberOwned(3)
                    .build();

    assertThat(generator.getNumberOwned()).isEqualTo(3);
  }
}
