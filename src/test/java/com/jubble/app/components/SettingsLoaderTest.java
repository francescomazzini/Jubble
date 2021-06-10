package com.jubble.app.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jubble.app.components.NewSettings;
import com.jubble.app.components.generator.SettingsLoader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;

public class SettingsLoaderTest {

    @Test
    public void shouldDeserialize() throws JsonProcessingException {
        String json = "{\n" +
                "  \"generators\" : [ {\n" +
                "    \"name\" : \"Stellar Panel\",\n" +
                "    \"description\" : \"\",\n" +
                "    \"level\" : 1,\n" +
                "    \"costBase\" : 3.738,\n" +
                "    \"rateGrowth\" : 1.07\n" +
                "  }, {\n" +
                "    \"name\" : \"Electron Absorber\",\n" +
                "    \"description\" : \"\",\n" +
                "    \"level\" : 2,\n" +
                "    \"costBase\" : 60.0,\n" +
                "    \"rateGrowth\" : 1.15\n" +
                "  } ],\n" +
                "  \"currencies\" : [ {\n" +
                "    \"owned\" : 0.0\n" +
                "  } ]\n" +
                "}";

        assertThat(NewSettings.getGenerators()).isEqualTo("Stellar Panel");

    }
    @Test
    public void shouldSerializeOut() throws IOException {
        NewSettings.getInstance();
        SettingsLoader.save();
    }
}
