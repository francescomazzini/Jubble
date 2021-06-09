package com.jubble.app.telegram.elements;

import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.components.generator.GeneratorsSingleton;
import com.jubble.app.utils.NumberNames;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeMessages {

    private final static List<String> listOfContentMessages = List.of(
            "Hi! Welcome to *Jubble*.\n Press the *button* below to start the game!",
            "The *game* is running.\n You can choose *one* of the *options* below to interact with the game",
            "The *game* has started.\n Press the *button* below to restart the game!",
            generateStatus()

    );


    public final static Map<String, TelegramMessage> listOfMessages = Map.of(
            "/start", new TelegramMessage(listOfContentMessages.get(0), Map.of("begin", "Begin")),
            "begin", new TelegramMessage(listOfContentMessages.get(1), Map.of("status", "Status",
                                                                                "shop", "Shop",
                                                                                "stop", "Stop")),
            "stop", new TelegramMessage(listOfContentMessages.get(2), Map.of("begin", "Begin")),
            "status", new TelegramMessage(listOfContentMessages.get(3), Map.of("begin", "Back"))

    );


    private static String generateStatus() {
        String stringMessage;
        stringMessage = ("*STATUS*: " +
                "\n *Balance*: " + NumberNames.createString(Balance.getPrimary()) +
                "\n *Total Production*: " +
                NumberNames.createString(GeneratorsSingleton.getGenerators()
                        .stream()
                        .mapToDouble(Generator::getProduction)
                        .sum())
                + " / s" +
                "\n\n *Generators Owned*: ");

        for(Generator gen : GeneratorsSingleton.getGenerators()) {
            stringMessage += "\n   • " + gen.getName() + ": " + gen.getNumberOwned();
        }

        return stringMessage;
    }

}
