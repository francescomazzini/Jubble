package com.jubble.app.components.generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.common.truth.Truth;
import org.eclipse.jgit.revwalk.LIFORevQueue;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class GeneratorLoaderTest {

    @Test
    public void shouldDeserializeJsonString() throws JsonProcessingException {
        String json = "[ {\n" +
                "  \"name\" : \"Stellar Panel\",\n" +
                "  \"description\" : \"\",\n" +
                "  \"level\" : 1,\n" +
                "  \"costBase\" : 3.738,\n" +
                "  \"rateGrowth\" : 1.07\n" +
                "}, {\n" +
                "  \"name\" : \"Electron Absorber\",\n" +
                "  \"description\" : \"\",\n" +
                "  \"level\" : 2,\n" +
                "  \"costBase\" : 60.0,\n" +
                "  \"rateGrowth\" : 1.15\n" +
                "} ]";

        List<Generator> expected = GeneratorLoader.deserialize(json);
        assertThat(expected.get(0).getName()).isEqualTo("Stellar Panel");

    }
}
