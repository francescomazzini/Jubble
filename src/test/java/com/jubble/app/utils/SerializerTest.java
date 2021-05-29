package com.jubble.app.utils;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SerializerTest {
  @Test
  public void shouldSerializeData() throws JsonProcessingException {
    GameProgress progress = new GameProgress(List.of(1, 2, 4), 23);
    String data = GameProgressHandler.serialize(progress);
    assertThat(data)
        .isEqualTo(
            "{\n" +
                    "  \"ownedGenerators\" : [ 1, 2, 4 ],\n" +
                    "  \"balance\" : 23.0\n" +
                    "}");
  }

  @Test
  public void shouldThrowIfNullIsPassed() {
    Exception expected = assertThrows(Exception.class, () -> GameProgressHandler.serialize(null));
    assertThat(expected.getMessage()).contains("Progress can't be null.");
  }
}
