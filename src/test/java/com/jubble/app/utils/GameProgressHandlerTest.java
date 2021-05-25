package com.jubble.app.utils;

import static org.junit.Assert.assertTrue;

import com.jubble.app.components.generator.Generator;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class GameProgressHandlerTest {
  List<Generator> list = Settings.getGenerators();
  GameProgress progress = new GameProgress(list, 0.0);

  @Test
  public void shouldSaveGameProgressSuccessfully() throws IOException {
    assertTrue(GameProgressHandler.saveGame(progress));
  }
}
