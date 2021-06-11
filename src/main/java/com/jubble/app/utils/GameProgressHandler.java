package com.jubble.app.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Saves and loads the game. */
public class GameProgressHandler {
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
    if (progress == null) throw new IllegalArgumentException("Progress can't be null.");
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
   */
  public static GameProgress loadGame() {
    ObjectMapper mapper = new ObjectMapper();

    try {
      return mapper.readValue(FILE, GameProgress.class);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
