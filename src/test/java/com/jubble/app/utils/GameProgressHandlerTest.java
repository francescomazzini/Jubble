package com.jubble.app.utils;

import com.jubble.app.components.generator.Generator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GameProgressHandlerTest {
    List<Generator> list = Settings.getGenerators();
    GameProgress progress = new GameProgress(list, 0.0);


    @Test
    public void shouldSaveGameProgressSuccessfully() throws IOException {
        assertTrue(GameProgressHandler.saveGame(progress));
    }

}
