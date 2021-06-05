package com.jubble.app.utils;

import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;

public class GameActions {

    public static void buyGenerator (int id, Balance primaryCurrency) {

        Generator currentGenerator = Assets.getInstance().getGenerators().get(id);

        if (primaryCurrency.getPrimary() > currentGenerator.getNextCost()) {
            primaryCurrency.setPrimary(primaryCurrency.getPrimary() - currentGenerator.getNextCost());
            currentGenerator.incrementNumberOwned();
        }

    }

}
