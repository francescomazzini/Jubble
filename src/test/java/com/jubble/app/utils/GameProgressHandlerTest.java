package com.jubble.app.utils;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameProgressHandlerTest {
 GameProgress progress = new GameProgress(List.of(1, 0, 0, 0, 0, 0), 0.0);
  GameProgress generators;
  String json;

  @BeforeEach
  void setup() throws JsonProcessingException {
    json = "{\n" + "  \"ownedGenerators\" : [ 1, 0, 0, 0, 0, 0 ],\n" + "  \"balance\" : 0\n" + "}";
    generators = new ObjectMapper().readValue(json, GameProgress.class);
  }

  @Test
  public void mockShouldBeNotNull() {
    assertThat(generators).isNotNull();
  }

  @Test
  public void balanceShouldBeEqual() {
    assertThat(generators.getBalance()).isEqualTo(progress.getBalance());
  }

  @Test
  public void generatorsListShouldBeEqual() {
    assertThat(generators.getOwnedGenerators()).isEqualTo(progress.getOwnedGenerators());
  }
}
