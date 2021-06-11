/*package com.jubble.app.utils;

import com.jubble.app.ThreadRunner;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.components.generator.GeneratorsSingleton;
import com.jubble.app.components.generator.IllegalOperationException;
import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SettingsTest {

    static String backupFile;
    static boolean didNotExist;

    @BeforeAll
    public static void backUp () throws IOException {
        if(Files.exists(Path.of(GameProgressHandler.PROGRESS_FILE_PATH)))
            backupFile = Files.readString(Path.of(GameProgressHandler.PROGRESS_FILE_PATH));
        else
            didNotExist = true;
    }

    @AfterAll
    public static void dispatch () throws IOException {
        if(!didNotExist)
            Files.writeString(Path.of(GameProgressHandler.PROGRESS_FILE_PATH), backupFile);
        else
            Files.delete(Path.of(GameProgressHandler.PROGRESS_FILE_PATH));
    }

    @Test
    @DisplayName("giftInitialAmount() should Increase numberOwned of 1st Generator By 1 If It is 0 (so If There Is No File Saved)")
    public void giftInitialAmountShouldIncreaseNrOfFirstGeneratorByOneIf0 () throws IOException {

        if(Files.exists(Path.of(GameProgressHandler.PROGRESS_FILE_PATH)))
            Files.delete(Path.of(GameProgressHandler.PROGRESS_FILE_PATH));

        ThreadRunner.run();

        Generator genFirst = GeneratorsSingleton.getGenerators().get(0);

        assertThat(genFirst.getNumberOwned()).isEqualTo(1);
        ThreadRunner.stop();
    }

    @Test
    @DisplayName("giftInitialAmount() should throw IllegalOperationException If numberOwned of 1st Generator > 0")
    public void giftInitialAmountShouldThrowIllegalOperationExceptionIfNumberOwnedOfFirstGenIsMoreThan0 () throws IOException {

        GameProgressHandler.saveGame(new GameProgress(List.of( 8, 0, 0, 0, 0, 0, 0, 0, 0, 0 ), 15.0));
        ThreadRunner.run();

        Generator genFirst = GeneratorsSingleton.getGenerators().get(0);

        ThreadRunner.stop();

        assertThrows(IllegalOperationException.class, () -> Settings.giftInitialAmount());
    }

}
*/