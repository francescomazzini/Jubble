# Jubble🚀

A "spacial" multithreading incremental game you can play via javafx or via a telegram bot.

Run Javafx app.

```
mvn clean compile exec:java@javafx
```

Run telegram bot.

```
mvn clean compile exec:java@telegram
```

Build project and verify code style

```
mvn clean verify
```

Generate code style report

```
mvn checkstyle:check
```

Temporal command for formatting:

```
mvn com.coveo:fmt-maven-plugin:format
```

# Implemented features:

- Abstract classes
- Generics (methods or classes)
- Custom exceptions
- Exception handling
- Test hooks (@beforeAll, @beforeEach. . . )
- File I/O
- Serialization (to JSON, XML, CSV)
- Deserialization (from JSON, XML, CSV)
- Collections
- Streams
- Multi threading
- Resource sharing (between threads)
- HTTP
- Asynchronous programming
- Method overriding

# Game mechanics
Basing on the typical model of tycoon games, the game is composed by:

- A main character (the player)
- An environment (the space)
- Unexpected events/accidents caused by external events where the player will have the possibility to choose how he should go ahead gaining pros or cons of their choice (not implemented)
- A Platonic goal to achieve by the player: accumulate resources endlessly.


The total production (or balance) is computed with:

$production_{total} = (production_{base} \times owned) \times multipliers$

_Where:_

$production_{base}$: costant of the $owned$: number of owned generators.

$multipliers$: current prestige of the player.

## References

1. A. Pecorella. (2016). The Math of Idle Games, Part I, II, III. [online] Available at: https://blog.kongregate.com/the-math-of-idle-games-part-i/ [Accessed 13 Jun. 2021].

## Next Steps

What could we add in the future versions:

- [ ] Implement the concept of derivative currencies.
- [ ] Add random events during the game experience.

## License

Chose a license.
