package com.jubble.app.utils;

import com.jubble.app.components.Balance;
import com.jubble.app.components.generator.Generator;
import com.jubble.app.components.generator.GeneratorsSingleton;

public class GameActions {

    public static void buyGenerator (Generator generator) {

        if (Balance.getPrimary() > generator.getNextCost()) {
            Balance.setPrimary(Balance.getPrimary() - generator.getNextCost());
            generator.incrementNumberOwned();
        }

    }

}
