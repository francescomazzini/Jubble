package com.jubble.app.core.utils;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameProgressTest {

  GameProgress progress = new GameProgress(List.of(1, 0, 0, 0, 0, 0), 15.0);
  static GameProgress progressDeserialized;
  static String jsonOfProgress;

  @BeforeAll
  static void setup() throws IOException {
    jsonOfProgress =
        "{\n" + "  \"ownedGenerators\" : [ 1, 0, 0, 0, 0, 0 ],\n" + "  \"balance\" : 15.0\n" + "}";
    progressDeserialized = new ObjectMapper().readValue(jsonOfProgress, GameProgress.class);
  }

  @Test
  @DisplayName("Generators Data retrieved by a JSon should not be null")
  public void dataFromJsonShouldNotBeNull() {
    assertThat(progressDeserialized).isNotNull();
  }

  @Test
  @DisplayName("Balance Value (15.0) retrieved by a JSon should be equal to 15.0")
  public void balanceFromJsonShouldBeRetrieved() {
    assertThat(progressDeserialized.getBalance()).isEqualTo(progress.getBalance());
  }

  @Test
  @DisplayName(
      "Generators List [ 1, 0, 0, 0, 0, 0 ] retrieved by a JSon should be equal to [ 1, 0, 0, 0, 0, 0 ]")
  public void generatorsFromJsonShouldBeRetrieved() {
    assertThat(progressDeserialized.getOwnedGenerators()).isEqualTo(progress.getOwnedGenerators());
  }
}
