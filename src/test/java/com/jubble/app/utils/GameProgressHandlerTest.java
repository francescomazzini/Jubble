package com.jubble.app.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jubble.app.components.generator.Generator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GameProgressHandlerTest {
    List<Generator> list = Settings.getGenerators();
    GameProgress progress = new GameProgress(list, 0.0);


    @Test
    public void shouldSaveGameProgressSuccessfully() throws IOException {
        assertTrue(GameProgressHandler.saveGame(progress));
    }

    @Test
    public void testMockDeserialization() throws JsonProcessingException {
        String json = "{\n" +
                "  \"allOwnedGenerators\" : [ {\n" +
                "    \"numberOwned\" : 0,\n" +
                "    \"name\" : \"Stellar Panel\",\n" +
                "    \"productionBase\" : 1.67,\n" +
                "    \"nextCost\" : 3.738,\n" +
                "    \"production\" : 0.0\n" +
                "  }, {\n" +
                "    \"numberOwned\" : 0,\n" +
                "    \"name\" : \"Electron Absorber\",\n" +
                "    \"productionBase\" : 20.0,\n" +
                "    \"nextCost\" : 60.0,\n" +
                "    \"production\" : 0.0\n" +
                "  }, {\n" +
                "    \"numberOwned\" : 0,\n" +
                "    \"name\" : \"Nucleus Extractor\",\n" +
                "    \"productionBase\" : 90.0,\n" +
                "    \"nextCost\" : 720.0,\n" +
                "    \"production\" : 0.0\n" +
                "  }, {\n" +
                "    \"numberOwned\" : 0,\n" +
                "    \"name\" : \"Hydrogenator\",\n" +
                "    \"productionBase\" : 360.0,\n" +
                "    \"nextCost\" : 8640.0,\n" +
                "    \"production\" : 0.0\n" +
                "  }, {\n" +
                "    \"numberOwned\" : 0,\n" +
                "    \"name\" : \"Dyson Sphere\",\n" +
                "    \"productionBase\" : 2160.0,\n" +
                "    \"nextCost\" : 103680.0,\n" +
                "    \"production\" : 0.0\n" +
                "  }, {\n" +
                "    \"numberOwned\" : 0,\n" +
                "    \"name\" : \"Black Hole Reverser\",\n" +
                "    \"productionBase\" : 6480.0,\n" +
                "    \"nextCost\" : 1244160.0,\n" +
                "    \"production\" : 0.0\n" +
                "  } ],\n" +
                "  \"balance\" : 0.0\n" +
                "}";

        GameProgress generators = new ObjectMapper().readValue(json, GameProgress.class);
        System.out.println(generators);
    }

}
