package com.jubble.app.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class GameProgressHandler {
  private static final String PATH = "game_progress.json";
  private static final File FILE = new File(PATH);

  public static boolean saveGame(GameProgress progress) throws IOException {
    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    try {
      mapper.writeValue(FILE, progress);
      System.out.println("Game progress saved");
      return true;
    } catch (IOException e) {
      throw new IOException("Game progress was not saved.");
    }
  }

  public static GameProgress loadGame() {
    // FIX: Game never loads.
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readValue(new File(PATH), GameProgress.class);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
