package com.jubble.app.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;

/** Saves and loads the game. */
public class GameProgressHandler {
  public static final String PROGRESS_FILE_PATH = "game_progress.json";
  private static final File FILE = new File(PROGRESS_FILE_PATH);

  /**
   * Save GameProgress into a file.
   *
   * @param progress
   */
  public static boolean saveGame(GameProgress progress) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    try {
      mapper.writeValue(FILE, progress);
      System.out.println("Game progress saved");
      return true;
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
    // FIX: Game never loads.
    ObjectMapper mapper = new ObjectMapper();

    try {
      return mapper.readValue(FILE, GameProgress.class);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
