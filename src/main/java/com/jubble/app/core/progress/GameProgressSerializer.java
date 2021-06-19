package com.jubble.app.core.progress;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/** Saves and loads the game. */
public final class GameProgressSerializer {
  /** Path of the file. */
  public static final String PROGRESS_FILE_PATH = "game_progress.json";

  private static final File FILE = new File(PROGRESS_FILE_PATH);

  /**
   * Returns the serialized JSON string of the parameter.
   *
   * @param progress game progress.
   * @return serialized object.
   * @throws JsonProcessingException if data doesn't match structure.
   */
  public static String serialize(GameProgress progress) throws JsonProcessingException {
    Objects.requireNonNull(progress);
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    return mapper.writeValueAsString(progress);
  }

  /**
   * Save the game progress in a file using JSON format.
   *
   * @param progress game progress.
   * @throws IOException if saving fails.
   */
  public static void saveGame(GameProgress progress) throws IOException {
    Objects.requireNonNull(progress);
    try {
      Path file = Path.of(PROGRESS_FILE_PATH);
      Files.writeString(file, serialize(progress));
    } catch (IOException e) {
      throw new IOException("Game progress was not saved.");
    }
  }

  /**
   * Loads the game from the saving file.
   *
   * @return loaded progress from file or null if file was found.
   * @throws IOException if the file was not found.
   */
  public static GameProgress loadGame() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(FILE, GameProgress.class);
  }
}
