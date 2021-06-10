package com.jubble.app.utils;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameProgressHandlerTest {
  GameProgress progress = new GameProgress(List.of(1, 0, 0, 0, 0, 0), 15.0);
  static GameProgress generators;
  static String json;

  @BeforeAll
  static void setup() throws JsonProcessingException {
    json = "{\n" + "  \"ownedGenerators\" : [ 1, 0, 0, 0, 0, 0 ],\n" + "  \"balance\" : 15.0\n" + "}";
    generators = new ObjectMapper().readValue(json, GameProgress.class);
  }

  @Test
  @DisplayName("Generators Data retrieved by a JSon should not be null")
  public void mockShouldBeNotNull() {
    assertThat(generators).isNotNull();
  }

  @Test
  @DisplayName("Balance Value (15.0) retrieved by a JSon should be equal to 15.0")
  public void balanceShouldBeEqual() {
    assertThat(generators.getBalance()).isEqualTo(progress.getBalance());
  }

  @Test
  @DisplayName("Generators List [ 1, 0, 0, 0, 0, 0 ] retrieved by a JSon should be equal to [ 1, 0, 0, 0, 0, 0 ]")
  public void generatorsListShouldBeEqual() {
    assertThat(generators.getOwnedGenerators()).isEqualTo(progress.getOwnedGenerators());
  }

  //check nulls
  //check data from the json

  @Test
  @DisplayName("if progress == null serialize should throw IllegalArgumentException")
  public void serializeNullProgressShouldThrowIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> GameProgressHandler.serialize(null));
  }

}
