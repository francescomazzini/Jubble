package com.jubble.app.telegram.messages;

import com.jubble.app.core.GameActions;
import com.jubble.app.core.Settings;
import com.jubble.app.core.resources.Balance;
import com.jubble.app.core.resources.generator.Generator;
import com.jubble.app.core.utils.NumberNamesUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for defining and generating the different types of message in the bot.
 */
public class TypeMessages {
  private TypeMessages() {}

  private static final Map<String, String> START_BUTTON =
      Map.of(MessageContent.CHOSE_OPTIONS.action(), "✅ Begin ");
  private static final Map<String, String> NAVIGATION_BUTTONS = Map.of("begin", " ◀️ Back");
  private static final Map<String, String> OPTION_MENU_BUTTONS =
      Map.of(
          MessageContent.OPEN_SHOP.action(),
          "\uD83D\uDCB8 Shop ",
          MessageContent.STATUS.action(),
          "\uD83D\uDD01 Status ",
          MessageContent.STOP_GAME.action(),
          "\uD83D\uDCDB Stop ");

  /**
   * It contains the pairs of a String (which represents the CallBackAction that can activate the
   * following message) and of a TelegramMessage (that represents the response of that
   * CallBackAction called)
   */
  public static final Map<String, TelegramMessage> listOfMessages =
      Map.of(
          MessageContent.WELCOME.action(),
          new TelegramMessage(MessageContent.WELCOME.message(), START_BUTTON),
          MessageContent.CHOSE_OPTIONS.action(),
          new TelegramMessage(MessageContent.CHOSE_OPTIONS.message(), OPTION_MENU_BUTTONS),
          MessageContent.STOP_GAME.action(),
          new TelegramMessage(MessageContent.STOP_GAME.message(), Map.of()),
          MessageContent.STATUS.action(),
          new TelegramMessage(MessageContent.STATUS.message(), NAVIGATION_BUTTONS),
          MessageContent.OPEN_SHOP.action(),
          new TelegramMessage(MessageContent.OPEN_SHOP.message(), NAVIGATION_BUTTONS),
          MessageContent.BUY_GEN.action(),
          new TelegramMessage(MessageContent.BUY_GEN.message(), NAVIGATION_BUTTONS));

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
                + NumberNamesUtil.createString(Balance.getPrimary())
                + "\n *Total Production* \uD83D\uDCC8: "
                + NumberNamesUtil.createString(GameActions.getSumTotalProductionGenerators())
                + " / s"
                + "\n\n *Generators Owned* \uD83D\uDE80: "));

    for (Generator gen : Settings.getGeneratorList()) {
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
                + NumberNamesUtil.createString(Balance.getPrimary())
                + "\n\n*Shop* \uD83D\uDCB8:"));

    for (int i = 0; i < GameActions.getNumberOfGenerators(); i++) {
      Generator gen = Settings.getGeneratorList().get(i);
      shopContentMessage
          .append("\n   ")
          .append(i + 1)
          .append(": ")
          .append(gen.getName())
          .append(", cost: ")
          .append(NumberNamesUtil.createString(gen.getNextCost()));
      shopContentMessage
          .append("\n        production: ")
          .append(NumberNamesUtil.createString(gen.getProductionBase()))
          .append(" / s");
    }

    return shopContentMessage.toString();
  }

  /**
   * Generates the shop buttons, linking to each button its specific key that refers to a generator.
   *
   * @return map of shop buttons.
   */
  public static Map<String, String> generateShopButtons() {
    Map<String, String> shopButtonsMessage = new HashMap<>();

    for (int i = 0; i < GameActions.getNumberOfGenerators(); i++) {
      shopButtonsMessage.put("gen" + i, (i + 1) + "");
    }

    shopButtonsMessage.putAll(NAVIGATION_BUTTONS);

    return shopButtonsMessage;
  }
}
