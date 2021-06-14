
# Game mechanics

Basing on the typical model of tycoon games, the game is composed by:

- A main character (the player)
- An environment (the space)
- Unexpected events/accidents caused by external events where the player will have the possibility to choose how he should go ahead gaining pros or cons of their choice (not implemented)
- A Platonic goal to achieve by the player: accumulate resources endlessly.


Primary currency: ↯
Is incremented every second basing of the number of owned generators.

Generator:
Item that produces the primary currency. 
There are different types of generator, each produces a certain amount of ↯ per second.

Production rate:
Ratio between ↯ production and costs. 

Multiplier:
Number of level achieved by the player. It ranges from 1 to n, where n is the number of generator level achieved by the player 

This is how we compute the cost of the next generator:
$`cost_{next} = cost_{base} \times (rate_{growth})^{owned}`$

This is computed the total production:
$`production_{total} = (production_{base} \times owned) \times multipliers`$




# Dependencies of the project
## Dependencies
- Truth: assertion library

## Devdependencies
- spotless: code formatter



