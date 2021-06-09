package com.jubble.app.telegram.elements;

import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.components.generator.GeneratorsSingleton;
import com.jubble.app.utils.NumberNames;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TypeMessages {

    private final static List<String> listOfContentMessages = List.of(
            "Hi! Welcome to *Jubble*.\nPress the *button* below to start the game!",
            "You can choose *one* of the *options* below to interact with the game",
            "The *game* has stopped.\nThank you for playing. Hope to see you soon!",
            "*STATUS*: ",
            "*SHOP*: ",
            "You have "

    );

    //forse treemap per pulsanti

    public final static Map<String, TelegramMessage> listOfMessages = Map.of(
            "/start", new TelegramMessage(listOfContentMessages.get(0), Map.of("begin", "Begin")),
            "begin", new TelegramMessage(listOfContentMessages.get(1), Map.of( "shop", "Shop",
                                                                                "status", "Status",
                                                                                "stop", "Stop")),
            "stop", new TelegramMessage(listOfContentMessages.get(2), Map.of()),
            "status", new TelegramMessage(listOfContentMessages.get(3), Map.of("begin", "Back")),
            "shop", new TelegramMessage(listOfContentMessages.get(4), Map.of("begin", "Back")),
            "gen", new TelegramMessage(listOfContentMessages.get(5), Map.of("shop", "Back"))

    );


    public static String generateStatusMessage() {
        String stringMessage;
        stringMessage = (
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

    public static String generateShopMessage() {
        String stringMessage;
        stringMessage = (
                "\n *Balance*: " + NumberNames.createString(Balance.getPrimary()) +
                        "\n\n*Shop*:");

        for(int i = 0; i < GeneratorsSingleton.getGenerators().size(); i++) {
            Generator gen = GeneratorsSingleton.getGenerators().get(i);
            stringMessage += "\n   " + (i+1) + ": " + gen.getName() + ", cost: " + NumberNames.createString(gen.getNextCost());
            stringMessage += "\n        production: " + NumberNames.createString(gen.getProductionBase()) + " / s";
        }

        return stringMessage;
    }

    public static Map<String, String> generateShopButtons() {

        Map<String, String> generatorButtons = new HashMap<>();

        for(int i = 0; i < GeneratorsSingleton.getGenerators().size(); i++)
            generatorButtons.put("gen" + i, (i+1) + "");

        generatorButtons.put("begin", "Back");

        return generatorButtons;
    }



}
