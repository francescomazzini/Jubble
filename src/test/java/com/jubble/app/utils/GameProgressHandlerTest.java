package com.jubble.app.utils;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameProgressHandlerTest {
  GameProgress progress = new GameProgress(List.of(1, 0, 0, 0, 0, 0), 15.0);
  static GameProgress progressDeserialized;
  static String jsonOfProgress;
  static Path jsonStoredPath = Path.of(GameProgressHandler.PROGRESS_FILE_PATH);

  @BeforeAll
  static void setup() throws JsonProcessingException {
    jsonOfProgress = "{\n" +
            "  \"ownedGenerators\" : [ 1, 0, 0, 0, 0, 0 ],\n" +
            "  \"balance\" : 15.0\n" +
            "}";
    progressDeserialized = new ObjectMapper().readValue(jsonOfProgress, GameProgress.class);
  }

  @Test
  @DisplayName("Generators Data retrieved by a JSon should not be null")
  public void mockShouldBeNotNull() {
    assertThat(progressDeserialized).isNotNull();
  }

  @Test
  @DisplayName("Balance Value (15.0) retrieved by a JSon should be equal to 15.0")
  public void balanceShouldBeEqual() {
    assertThat(progressDeserialized.getBalance()).isEqualTo(progress.getBalance());
  }

  @Test
  @DisplayName("Generators List [ 1, 0, 0, 0, 0, 0 ] retrieved by a JSon should be equal to [ 1, 0, 0, 0, 0, 0 ]")
  public void generatorsListShouldBeEqual() {
    assertThat(progressDeserialized.getOwnedGenerators()).isEqualTo(progress.getOwnedGenerators());
  }


  /**
   * This test checks equality directly from the file with the jsonOfProgress.
   * .replaceAll("\\r", "") is needed on both because the content may be equal
   * but the type of line breaks could be different.
   */
  @Test
  @DisplayName("Saving Progress ([ 1, 0, 0, 0, 0, 0 ], 15.0) Should Be Serialized Correctly")
  public void savingProgressShouldBeSerializedCorrectly() throws IOException {
    String initialFileState = "";
    if(Files.exists(jsonStoredPath))
      initialFileState= Files.readString(jsonStoredPath);

    GameProgressHandler.saveGame(progress);
    assertThat(Files.readString(jsonStoredPath).replaceAll("\\r", "")).isEqualTo(jsonOfProgress.replaceAll("\\r", ""));

    if(!initialFileState.equals(""))
      Files.writeString(jsonStoredPath, initialFileState);
    else
      Files.delete(jsonStoredPath);
  }

  /*
  @Test
  @DisplayName("Loading Progress ([ 1, 0, 0, 0, 0, 0 ], 15.0) Should Be Deserialized Correctly")
  public void loadingProgressShouldBeDeserializedCorrectly() throws IOException {
    progress

    GameProgressHandler.saveGame(progress);
    assertThat(Files.readString(jsonStoredPath).replaceAll("\\r", "")).isEqualTo(jsonOfProgress.replaceAll("\\r", ""));

    if(!initialFileState.equals(""))
      Files.writeString(jsonStoredPath, initialFileState);
    else
      Files.delete(jsonStoredPath);
  } */

  @Test
  @DisplayName("if progress == null serialize should throw IllegalArgumentException")
  public void serializeNullProgressShouldThrowIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> GameProgressHandler.serialize(null));
  }

}
