package com.jubble.app.utils;

import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.components.generator.GeneratorsSingleton;

public class GameActions {

    public static void buyGenerator (int id) {

        Generator currentGenerator = GeneratorsSingleton.getGenerators().get(id);

        if (Balance.getPrimary() > currentGenerator.getNextCost()) {
            Balance.setPrimary(Balance.getPrimary() - currentGenerator.getNextCost());
            currentGenerator.incrementNumberOwned();
        }

    }

}
