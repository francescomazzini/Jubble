package com.jubble.app.components.generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.jubble.app.utils.GameProgress;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GeneratorLoader {
    public static final String GENERATORS_FILE_PATH = "src/main/resources/generators.json";

    public static List<Generator> deserialize(String json) throws JsonProcessingException {
        JsonMapper mapper = new JsonMapper();
        return mapper.readValue(json, new TypeReference<>() {
        });
    }

    private static String loadFile() throws IOException {
        Path path = Path.of(GENERATORS_FILE_PATH);
        return new String(Files.readAllBytes(path));
    }

    public static void load() throws FileNotFoundException, JsonProcessingException {
        try {
            deserialize(loadFile());
        } catch (JsonProcessingException jsonError) {
            throw jsonError;
        } catch (IOException fileError) {
            throw new FileNotFoundException("Generators config file was not found, unable to start the game.");
        }
    }

    /**
     * Returns the serialized JSON string of the parameter.
     *
     * @return serialized object.
     * @throws JsonProcessingException if data doesn't match structure.
     */
    public static String serialize() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        return mapper.writeValueAsString(GeneratorsSingleton.getGenerators());
    }

    public static void testsaveGenerators() throws IOException {
        try {
            Path file = Path.of(GENERATORS_FILE_PATH);
            Files.writeString(file, serialize());
        } catch (IOException e) {
            throw new IOException("Game progress was not saved.");
        }
    }

}
