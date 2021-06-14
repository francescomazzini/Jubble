
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

# Implemented features:

- Abstract classes: needed to separate the various calculation methods and the normal variables of a generator. 
- Generics (methods or classes): following the advices provided in effective Java, we generified a builder pattern. 
- Custom exceptions: IllegalOperationException.
- Exception handling: 
Like in Json file reading. We had to "mute" some exceptions where throwing them would have stopped the app without any meaning. In those situation, we have written the cause in the method. 
- Test hooks (@beforeAll, @beforeEach. . . )
- File I/O
- Serialization (to JSON, XML, CSV), Deserialization (from JSON, XML, CSV): storing the game progress. Done using Jackson.
- Collections: principally lists and maps.
- Streams
- Multi threading
- Resource sharing (between threads)
- HTTP
- Asynchronous programming: implemented in the telegram bot.
- Method overriding
- Singleton pattern: Balance class.
- Builder pattern: Generator.
- Facade pattern

## References

1. A. Pecorella. (2016). The Math of Idle Games, Part I, II, III. [online] Available at: https://blog.kongregate.com/the-math-of-idle-games-part-i/ [Accessed 13 Jun. 2021].
2. J. Bloch. (2017). Effective Java: Programming Language Guide, third edition.

## Next Steps

What could we add in the future versions:

- [ ] Implement the concept of derivative currencies.
- [ ] Add random events during the game experience.





# Dependencies of the project
## Dependencies
- Truth: assertion library

## Devdependencies
- spotless: code formatter



