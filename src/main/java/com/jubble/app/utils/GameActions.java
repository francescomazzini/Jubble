package com.jubble.app.utils;

import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.components.generator.GeneratorsSingleton;

public class GameActions {

    public static void buyGenerator (int id, Balance primaryCurrency) {

        Generator currentGenerator = GeneratorsSingleton.getGenerators().get(id);

        if (primaryCurrency.getPrimary() > currentGenerator.getNextCost()) {
            primaryCurrency.setPrimary(primaryCurrency.getPrimary() - currentGenerator.getNextCost());
            currentGenerator.incrementNumberOwned();
        }

    }

}
