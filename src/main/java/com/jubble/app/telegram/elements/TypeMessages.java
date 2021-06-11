package com.jubble.app.telegram.elements;

import com.jubble.app.core.components.Balance;
import com.jubble.app.core.components.generator.Generator;
import com.jubble.app.core.utils.NumberNames;
import com.jubble.app.core.Settings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeMessages {

    private final static List<String> listOfContentMessages = List.of(
            "Hi! Welcome to *Jubble* \uD83D\uDC4B.\n\nPress the *button* below to start the game! \uD83D\uDC7D",
            "You can choose *one* of the *options* below to interact with the game \uD83D\uDC47",
            "The *game* has stopped. \uD83D\uDCDB\n\nThank you for playing. Hope to see you soon! ✌️",
            "*STATUS*: ",
            "*SHOP*: ",
            "You have "

    );

    public final static Map<String, TelegramMessage> listOfMessages = Map.of(
            "/start", new TelegramMessage(listOfContentMessages.get(0), Map.of("begin", "✅ Begin ")),
            "begin", new TelegramMessage(listOfContentMessages.get(1), Map.of( "shop", "\uD83D\uDCB8 Shop ",
                                                                                "status", "\uD83D\uDD01 Status ",
                                                                                "stop", "\uD83D\uDCDB Stop ")),
            "stop", new TelegramMessage(listOfContentMessages.get(2), Map.of()),
            "status", new TelegramMessage(listOfContentMessages.get(3), Map.of("begin", " ◀️ Back")),
            "shop", new TelegramMessage(listOfContentMessages.get(4), Map.of("begin", " ◀️ Back")),
            "gen", new TelegramMessage(listOfContentMessages.get(5), Map.of("shop", " ◀️ Back"))

    );

    /**
     * It generates the status telegram message content. It is needed to be generated because it changes in
     * run time and it is also quite complex.
     *
     * @return statusContentMessage
     */
    public static String generateStatusContent() {
        String statusContentMessage;
        statusContentMessage = (
                "\n *Balance* \uD83D\uDCB0: " + NumberNames.createString(Balance.getPrimary()) +
                "\n *Total Production* \uD83D\uDCC8: " +
                NumberNames.createString(Settings.getGenerators()
                        .stream()
                        .mapToDouble(Generator::getProduction)
                        .sum())
                + " / s" +
                "\n\n *Generators Owned* \uD83D\uDE80: ");

        for(Generator gen : Settings.getGenerators()) {
            statusContentMessage += "\n   • " + gen.getName() + ": " + gen.getNumberOwned();
        }

        return statusContentMessage;
    }

    /**
     * It generates the shop telegram message content. It is needed to be generated because it changes in
     * run time and it is also quite complex.
     *
     * @return statusContentMessage
     */
    public static String generateShopContent() {
        String shopContentMessage;
        shopContentMessage = (
                "\n *Balance* \uD83D\uDCB0: " + NumberNames.createString(Balance.getPrimary()) +
                        "\n\n*Shop* \uD83D\uDCB8:");

        for(int i = 0; i < Settings.getGenerators().size(); i++) {
            Generator gen = Settings.getGenerators().get(i);
            shopContentMessage += "\n   " + (i+1) + ": " + gen.getName() + ", cost: " + NumberNames.createString(gen.getNextCost());
            shopContentMessage += "\n        production: " + NumberNames.createString(gen.getProductionBase()) + " / s";
        }

        return shopContentMessage;
    }

    /**
     * It generates the shop telegram message buttons. It is needed to be generated because
     * it is also quite complex and each button need its specific key which should refer to its generator.
     *
     * @return
     */
    public static Map<String, String> generateShopButtons() {

        Map<String, String> shopButtonsMessage = new HashMap<>();

        for(int i = 0; i < Settings.getGenerators().size(); i++)
            shopButtonsMessage.put("gen" + i, (i+1) + "");

        shopButtonsMessage.put("begin", " ◀️ Back");

        return shopButtonsMessage;
    }


}
