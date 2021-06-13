package com.jubble.app.core.utils;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.core.JsonProcessingException;
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
  static String jsonOfProgress =
      "{\n" + "  \"ownedGenerators\" : [ 1, 0, 0, 0, 0, 0 ],\n" + "  \"balance\" : 15.0\n" + "}";
  static Path jsonStoredPath = Path.of(GameProgressSerializer.PROGRESS_FILE_PATH);
  static String initialFileState;

  @BeforeAll
  static void setup() throws IOException {
    initialFileState = "";
    if (Files.exists(jsonStoredPath)) initialFileState = Files.readString(jsonStoredPath);
  }

  @AfterAll
  static void resetFile() throws IOException {
    if (!initialFileState.equals("")) Files.writeString(jsonStoredPath, initialFileState);
    else Files.delete(jsonStoredPath);
  }

  /**
   * This test checks equality directly from the file with the jsonOfProgress. .replaceAll("\\r",
   * "") is needed on both because the content may be equal but the type of line breaks could be
   * different.
   */
  @Test
  @DisplayName("Serializing Progress ([ 1, 0, 0, 0, 0, 0 ], 15.0) Should Be Serialized Correctly")
  public void serializingProgressShouldBeSerializedCorrectly() throws JsonProcessingException {
    String data = GameProgressSerializer.serialize(progress);
    assertThat(data.replaceAll("\\r", "")).isEqualTo(jsonOfProgress.replaceAll("\\r", ""));
  }

  @Test
  @DisplayName(
      "Saving Progress in File ([ 1, 0, 0, 0, 0, 0 ], 15.0) Should Be Serialized Correctly")
  public void savingProgressInFileShouldBeSerializedCorrectly() throws IOException {
    GameProgressSerializer.saveGame(progress);
    assertThat(Files.readString(jsonStoredPath).replaceAll("\\r", ""))
        .isEqualTo(jsonOfProgress.replaceAll("\\r", ""));
  }

  @Test
  @DisplayName(
      "Saving then Loading Progress from File ([ 1, 0, 0, 0, 0, 0 ], 15.0) Should Be Deserialized Correctly")
  public void savingThenLoadingProgressFromFileShouldBeDeserializedCorrectly() throws IOException {

    GameProgressSerializer.saveGame(progress);

    GameProgress progressFromFile = GameProgressSerializer.loadGame();
    assertAll(
        () ->
            assertThat(progressFromFile.getOwnedGenerators())
                .isEqualTo(progress.getOwnedGenerators()),
        () -> assertThat(progressFromFile.getBalance()).isEqualTo(progress.getBalance()));
  }

  @Test
  @DisplayName("if progress == null serialize should throw IllegalArgumentException")
  public void serializeNullProgressShouldThrowIllegalArgumentException() {
    Exception expected =
        assertThrows(IllegalArgumentException.class, () -> GameProgressSerializer.serialize(null));
    assertThat(expected.getMessage()).contains("Progress can't be null.");
  }
}
