package com.jubble.app.utils;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberNamesTest {

  @Test
  @DisplayName("createString(1453.354) should become 1.45 K ")
  public void createStringShouldReturnThousandNumberWithLetterK () {
    assertThat(NumberNames.createString(1453.354)).isEqualTo("1.45 K ");
  }

  @Test
  @DisplayName("createString(1,034,000,453.354) should become 1.03 B ")
  public void createStringShouldReturnBillionNumberWithLetterB () {
    assertThat(NumberNames.createString(1034000453.354)).isEqualTo("1.03 B ");
  }

  @Test
  @DisplayName("createString(26.34) should become 26.34 ")
  public void createStringShouldReturnSmallNumbersWithoutLetter () {
    assertThat(NumberNames.createString(26.34)).isEqualTo("26.34 ");
  }

  @Test
  @DisplayName("if NUM > Q createString(3,213,324,234,341,034,000,453.354) should become 3,213,324.23 Q ")
  public void createStringShouldReturnBiggerNumbersThanQuadrillionWithQandWithCommas () {
    assertThat(NumberNames.createString(3213324234341034000453.354)).isEqualTo("3,213,324.23 Q ");
  }

  @Test
  @DisplayName("createString(34) should become 34.00 ")
  public void createStringShouldReturnIntegersWithDecimalPart () {
    assertThat(NumberNames.createString((double) 34)).isEqualTo("34.00 ");
  }

  @Test
  @DisplayName("createString(-1,324,324.23) should become -1.32 M ")
  public void createStringShouldReturnMillionNegativeNumberWithLetterM () {
    assertThat(NumberNames.createString(-1324324.23)).isEqualTo("-1.32 M ");
  }

  @Test
  @DisplayName("createString(0.0) should become 0.00 ")
  public void createString0ShouldReturn0 () {
    assertThat(NumberNames.createString(0.0)).isEqualTo("0.00 ");
  }

  @Test
  @DisplayName("createString(1.678) should become 1.68 ")
  public void createStringShouldApproximateCorrectly () {
    assertThat(NumberNames.createString(1.678)).isEqualTo("1.68 ");
  }

  @Test
  @DisplayName("createString(18,899,545.678) should become 18.90 M ")
  public void createStringShouldApproximateCorrectlyAlsoWithBigNumbers () {
    assertThat(NumberNames.createString(18899545.678)).isEqualTo("18.90 M ");
  }

  @Test
  @DisplayName("createString(0.009999999) should become 0.01 ")
  public void createStringShouldApproximateCorrectlyAlsoWithSmallNumbers () {
    assertThat(NumberNames.createString(0.009999999)).isEqualTo("0.01 ");
  }

  @Test
  @DisplayName("createString(-899,545.678) should become -899.55 K ")
  public void createStringShouldApproximateCorrectlyAlsoWithNegativeNumbers () {
    assertThat(NumberNames.createString(-899545.678)).isEqualTo("-899.55 K ");
  }

}
