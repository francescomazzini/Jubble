# Jubble🚀

## Group Members

@Francesco.Mazzini
Alberto Defendi  

## What is Jubble?

Jubble is a "spacial" multithreading incremental game, also known as clicker game or an idle game. It is a videogame whose gameplay consists of the player performing simple actions such as clicking on the screen repeatedly. This “grinding” earns the player in-game currency which can be used to increase the rate of currency acquisition. 

You can play it via javafx or via a telegram bot.  
If you want to play it you have to run the command written below (which start the game server) and then you can go on telegram, search for "@jubble_bot" and start it (as a client).  
Be aware that the server of telegram bot can only have one client, because we did not want to create a more complicated server able to manage more requests and with a database of all the data from every player. Through telegram bot we only wanted to show that the core of the game is separated from the User Interfaces used. Therefore you can play the game with javafx, then close it and resume your session using the telegram bot (using same data!).

## Commands

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

##

## Game mechanics

Based on the typical model of tycoon games, the game is composed by:

- A main character (the player)
- An environment (the space)
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

## Implemented features:

- Abstract classes: needed to separate the various calculation methods and the normal variables of a generator. 
- Generics (methods or classes): following the advices provided in effective Java, we generified a builder pattern. 
- Collections: principally lists and maps.
- Custom exceptions: IllegalOperationException.
- Exception handling: 
Like in Json file reading. We had to "mute" some exceptions where throwing them would have stopped the app without any meaning. In those situation, we have written the cause in the method. 
- Method overriding
- Lambdas
- Streams
- File I/O
- Serialization (to JSON, XML, CSV) to store game progress (Jackson)
- Deserialization (from JSON, XML, CSV) to recover game progress (Jackson)
- Multithreading
- Resource sharing (between threads)
- Test hooks (@beforeAll, @beforeEach. . . )

- Singleton pattern: Balance class.
- Builder pattern: Generator class.


## Dependencies of the project
### Dependencies
- Truth: assertion library

### Devdependencies
- spotless: code formatter



## Next Steps

What could we add in the future versions:

- [ ] Implement the concept of derivative currencies.
- [ ] Add random events during the game experience.

## References

1. A. Pecorella. (2016). The Math of Idle Games, Part I, II, III. [online] Available at: https://blog.kongregate.com/the-math-of-idle-games-part-i/ [Accessed 13 Jun. 2021].
2. J. Bloch. (2017). Effective Java: Programming Language Guide, third edition.



## License

Chose a license.
