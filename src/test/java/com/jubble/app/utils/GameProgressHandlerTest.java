package com.jubble.app.utils;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameProgressHandlerTest {
  GameProgress progress = new GameProgress(List.of(1, 0, 0, 0, 0, 0), 15.0);
  static GameProgress progressDeserialized;
  static String jsonOfProgress;
  static Path jsonStoredPath = Path.of(GameProgressHandler.PROGRESS_FILE_PATH);
  static String initialFileState;

  @BeforeAll
  static void setup() throws IOException {
    initialFileState = "";
    if(Files.exists(jsonStoredPath))
      initialFileState= Files.readString(jsonStoredPath);

    jsonOfProgress = "{\n" +
            "  \"ownedGenerators\" : [ 1, 0, 0, 0, 0, 0 ],\n" +
            "  \"balance\" : 15.0\n" +
            "}";
    progressDeserialized = new ObjectMapper().readValue(jsonOfProgress, GameProgress.class);
  }

  @AfterAll
  static void resetFile() throws IOException {
    if (!initialFileState.equals(""))
      Files.writeString(jsonStoredPath, initialFileState);
    else
      Files.delete(jsonStoredPath);
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
  @DisplayName("Generators List [ 1, 0, 0, 0, 0, 0 ] retrieved by a JSon should be equal to [ 1, 0, 0, 0, 0, 0 ]")
  public void generatorsFromJsonShouldBeRetrieved() {
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

    GameProgressHandler.saveGame(progress);
    assertThat(Files.readString(jsonStoredPath).replaceAll("\\r", ""))
            .isEqualTo(jsonOfProgress.replaceAll("\\r", ""));

  }


  @Test
  @DisplayName("Saving then Loading Progress ([ 1, 0, 0, 0, 0, 0 ], 15.0) Should Be Deserialized Correctly")
  public void savingThenLoadingProgressShouldBeDeserializedCorrectly() throws IOException {

    GameProgressHandler.saveGame(progress);

    GameProgress progressFromFile = GameProgressHandler.loadGame();
    assertAll(
            () -> assertThat(progressFromFile.getOwnedGenerators()).isEqualTo(progress.getOwnedGenerators()),
            ()  -> assertThat(progressFromFile.getBalance()).isEqualTo(progress.getBalance())
    );

  }

  @Test
  @DisplayName("if progress == null serialize should throw IllegalArgumentException")
  public void serializeNullProgressShouldThrowIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> GameProgressHandler.serialize(null));
  }

}
