# Jubble🚀

## Group Members

Francesco Mazzini (@Francesco.Mazzini)
Alberto Defendi (@ahl-berto)

## What is Jubble?

Jubble is a "spacial" multithreading incremental game, also known as clicker game or an idle game. It is a videogame whose gameplay consists of the player performing simple actions such as clicking on the screen repeatedly. This “grinding” earns the player in-game currency which can be used to increase the rate of currency acquisition.

You can play it via javafx or via a telegram bot.
If you want to play it using Telegram you have to run the specific command written below (which start the game server) and then you can go on telegram, search for "@jubble_bot" and start it (as a client).
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

Generate code style report (the errors reported are suggestions and should not be considered as the only as absolute truth)

```
mvn checkstyle:check
```

Temporal command for formatting:

```
mvn com.coveo:fmt-maven-plugin:format
```

## Gameplay

In Jubble, a little Astronaut landed on a random planet because of a problem with his rocket. At the beginnning he only has a little stellar panel to gain some Energy. But this is obviously not enough to repair and make his rocket able to be launched again in the space. He needs to collect more and more energy in order to do so! In the shop, the player can also buy using Energy more generators with different production rate!

The goal is platonic and it is to reach as more energy as the player can. Depending on how the player will buy generators, his production will be different, being more or less efficient!

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

## Code Structure:

thihngs

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

## Interfaces

thhingsss

## Dependencies of the project
### Dependencies
- Truth: assertion library
- Javafx
- Telegram

### Devdependencies
- spotless: code formatter

## Our Experience
### Internal Organization
We didn't decide at the beginning any special organization between us. We only wanted to try things, discover new techniques and create what we thought it could have been useful for the project.

In a more advanced part of the development, we divided a bit the jobs, but just to avoid many conflicts. For example Francesco spent a bit more time in the interfaces (when the main core was ready) while Alberto continued developing in the core adding loading and saving the game. Each time we thought it was needed to focus on different things, we divided jobs to do, otherwise we only advised each other on what we were working, based on we wanted to do for curiosity or because of specific ideas.

### Branch Strategy
Since the beginning, we approached the development on different branches, using a flexible policy but keeping the principle of having the working code on two branches.

- Master: containing only working code with at least a playable gameplay. We loaded here the very first version of the game, when it was only playable in CLI.

- Dev_main: containing the stable code of the project, merged from side branches created for every small feature when possible.

- Feature/XXXX: a generic example of the many branches we had (that we cleaned) where XXXX was the name of the feature.

- NrIssue-name-issue: another generic example of branches created in order to satisfy a specific issue (we started using them only at the end).

Close to the end of the project, where the code was starting to becoming more stable and we learned the best practices, we started using gitlab issues, in order to keep the focus on the remaining features and track better the work.
From this, in the final days we extended the branching techniques to side branches that were opened directly from the issues.

### Main Challenge Faced

Francesco: "I think I had two main challenges while developing the game. The first came up when I realised that the game needed to be more fexible and more adaptable to internal change adding another generator. The most difficult part of that was making the interface (especially javafx one) to adapt to a change like this or to the possibility to have an infinite number (not really infinite) of generators. The second came up instead when I started to see that the code I wrote was too difficult to be read, even by me. At that point, Alberto and I, started a quite long period of cleaning code. We decide that the way the code is wrote is more important than the number of features actually implemented. Even if this part can seem easy and relaxing, it is not and it is also much stressful because I lost much time thinking on how things should be done, trying to follow the most correct logical approach. What I mean is, for example, asking myself if a class should be a util class or maybe a singleton class or just a normal class, or another example trying to understand if some classes were too big and how decompos them in other subclasses (which need to be logically correct, sharing variables that were logical to be there together)"

Alberto:


## Next Steps

What could we add in the future versions:

- [ ] Implement the concept of derivative currencies (secondary currencies that can be exchanged with the primary and gainable from different type of generators).
- [ ] Add random events during the game experience.
- [ ] Add minigames which allow to bet using the primary currency, risking to lose it or gain more of it.

## Youtube Video

Link: ()[]


## References

1. A. Pecorella. (2016). The Math of Idle Games, Part I, II, III. [online] Available at: https://blog.kongregate.com/the-math-of-idle-games-part-i/ [Accessed 13 Jun. 2021].
2. J. Bloch. (2017). Effective Java: Programming Language Guide, third edition.



## License

Chose a license.
