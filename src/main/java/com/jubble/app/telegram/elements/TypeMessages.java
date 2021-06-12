package com.jubble.app.telegram.elements;

import com.jubble.app.core.Settings;
import com.jubble.app.core.components.Balance;
import com.jubble.app.core.components.generator.Generator;
import com.jubble.app.core.utils.GameActions;
import com.jubble.app.core.utils.NumberNames;
import java.util.HashMap;
import java.util.Map;

public class TypeMessages {

  public static final Map<String, TelegramMessage> listOfMessages =
      Map.of(
          "/start",
              new TelegramMessage(MessageContent.WELCOME.getMessage(), Map.of("begin", "✅ Begin ")),
          "begin",
              new TelegramMessage(
                  MessageContent.CHOSE_OPTIONS.getMessage(),
                  Map.of(
                      "shop",
                      "\uD83D\uDCB8 Shop ",
                      "status",
                      "\uD83D\uDD01 Status ",
                      "stop",
                      "\uD83D\uDCDB Stop ")),
          "stop", new TelegramMessage(MessageContent.STOP_GAME.getMessage(), Map.of()),
          "status",
              new TelegramMessage(MessageContent.STATUS.getMessage(), Map.of("begin", " ◀️ Back")),
          "shop",
              new TelegramMessage(
                  MessageContent.OPEN_SHOP.getMessage(), Map.of("begin", " ◀️ Back")),
          "gen",
              new TelegramMessage(
                  MessageContent.CHECK_BALANCE.getMessage(), Map.of("shop", " ◀️ Back")));

  /**
   * It generates the status telegram message content. It is needed to be generated because it
   * changes in run time and it is also quite complex.
   *
   * @return statusContentMessage
   */
  public static String generateStatusContent() {
    StringBuilder statusContentMessage;
    statusContentMessage =
        new StringBuilder(
            ("\n *Balance* \uD83D\uDCB0: "
                + NumberNames.createString(Balance.getPrimary())
                + "\n *Total Production* \uD83D\uDCC8: "
                + NumberNames.createString(GameActions.getTotalGeneratorsSum())
                + " / s"
                + "\n\n *Generators Owned* \uD83D\uDE80: "));

    for (Generator gen : Settings.getGenerators()) {
      statusContentMessage
          .append("\n   • ")
          .append(gen.getName())
          .append(": ")
          .append(gen.getNumberOwned());
    }

    return statusContentMessage.toString();
  }

  /**
   * It generates the shop telegram message content. It is needed to be generated because it changes
   * in run time and it is also quite complex.
   *
   * @return statusContentMessage
   */
  public static String generateShopContent() {
    StringBuilder shopContentMessage;
    shopContentMessage =
        new StringBuilder(
            ("\n *Balance* \uD83D\uDCB0: "
                + NumberNames.createString(Balance.getPrimary())
                + "\n\n*Shop* \uD83D\uDCB8:"));

    for (int i = 0; i < Settings.getGenerators().size(); i++) {
      Generator gen = Settings.getGenerators().get(i);
      shopContentMessage
          .append("\n   ")
          .append(i + 1)
          .append(": ")
          .append(gen.getName())
          .append(", cost: ")
          .append(NumberNames.createString(gen.getNextCost()));
      shopContentMessage
          .append("\n        production: ")
          .append(NumberNames.createString(gen.getProductionBase()))
          .append(" / s");
    }

    return shopContentMessage.toString();
  }

  /**
   * It generates the shop telegram message buttons. It is needed to be generated because it is also
   * quite complex and each button need its specific key which should refer to its generator.
   *
   * @return
   */
  public static Map<String, String> generateShopButtons() {

    Map<String, String> shopButtonsMessage = new HashMap<>();

    for (int i = 0; i < Settings.getGenerators().size(); i++)
      shopButtonsMessage.put("gen" + i, (i + 1) + "");

    shopButtonsMessage.put("begin", " ◀️ Back");

    return shopButtonsMessage;
  }
}
