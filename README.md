# Jubble🚀

## Group Members

Francesco Mazzini (@Francesco.Mazzini)
Alberto Defendi (@ahl-berto)

## What is Jubble?

Jubble is a "spacial" multithreading incremental game, also known as clicker game or an idle game. It is a videogame whose gameplay consists of the player performing simple actions such as clicking on the screen repeatedly. This “grinding” earns the player in-game currency which can be used to increase the rate of currency acquisition.

You can play it via javafx or via a telegram bot.
If you want to play it using Telegram you have to run the specific command written below (which start the game server) and then you can go on telegram, search for "@jubble_bot" and start it (as a client).
Be aware that the server of telegram bot can only have one client, because we did not want to create a more complicated server able to manage more requests and with a database of all the data from each player. Through telegram bot we only wanted to show that the core of the game is separated from the User Interfaces used. Therefore you can play the game with javafx, then close it and resume your session using the telegram bot (using same data!).

## Commands

Run Javafx app.

```
mvn clean compile exec:java@javafx
```

Run telegram bot as server (to stop it you need to press Ctrl+C/Cmnd+C and confirm).

```
mvn clean compile exec:java@telegram
```

Clean project.

```
mvn clean
```

Run project tests and generate coverage report.

```
mvn test
```

Generate executable Jars (one for JAVAFX, another for TELEGRAM).

```
mvn package
```

Generate JAVADOC (then you can find it at target/site/apidocs/index.html).

```
mvn javadoc:javadoc
```

Build project and verify code style


Generate code style report (the errors reported are suggestions and should not be considered as the only as absolute truth)

```
mvn checkstyle:check
```


## Gameplay

In Jubble, a little Astronaut landed on a planet after having run out of fuel.
This planet is mostly inhabited, and no one can help. At the beginning he (or she) is left only with a little stellar panel, which makes possible to recover some Energy to back home, but is obviously not enough to make his rocket able to travel for millions of light years in the space.
More and more energy is needed in order to do so! In the shop, the player can also buy generators with higher production rate to make the way to home faster!
The endless goal is to reach as more energy as the player can. Depending on how the player will buy generators, his production will be different, being more or less efficient!

## Game mechanics

Based on the typical model of tycoon games, the game is composed by:

- A main character (the player)
- An environment (the space)
- A Platonic goal to achieve by the player: accumulate resources endlessly.

Primary currency: ↯
Is incremented each second basing of the number of owned generators.

Generator:
Item that produces the primary currency.
There are different types of generator, each produces a certain amount of ↯ per second.

Production rate:
Ratio between ↯ production and costs.

Multiplier:
Number of level achieved by the player. It ranges from 1 to n, where n is the number of generator level achieved by the player.

This is how we compute the cost of the next generator:
$`cost_{next} = cost_{base} \times (rate_{growth})^{owned}`$

This is how the total production is computed:
$`production_{total} = (production_{base} \times owned)`$

## Code Structure:

In brief, the code is splitted in three folders:
- core
- javafx
- telegram

The core is the core logic of the game and constitutes an api that can be accessed to the clients.
Java and Telegram have different entrypoint, and are based on the "core". They can be run using the command specified above.

### Telegram
The telegram bot doesn't need a configuration, since we have already prepared a Token linked to a bot named "@jubble_bot".
However, since [bot tokens should remain secret](https://github.com/rubenlagus/TelegramBots/wiki/Handling-Bot-Tokens#-bot-token-rules), should you want to fork this project or to self host it, we recommend replacing the token with a personal one.

A detailed representation of the packages and classes can be found in the generated Javadoc. A brief information on the dependencies of the project can be found in the pom.xml file.

## Implemented features:

- Abstract classes: needed to separate the various calculation methods and the normal variables of a generator.
- Generics (methods or classes): following the advices provided in effective Java, we generified a builder pattern.
- Collections: principally lists and maps.
- Custom exceptions: IllegalOperationException.
- Exception handling:
Like in Json file reading. We had to "mute" some exceptions where throwing them would have stopped the app without any meaning. In those situations, we motivated the decision in a comment.
- Method overriding
- Lambdas
- Streams
- File I/O
- Serialization (to JSON, XML, CSV) to store game progress (Jackson)
- Deserialization (from JSON, XML, CSV) to recover game progress (Jackson)
- Multithreading
- Resource sharing (between threads): In Balance class.
- Test hooks (@beforeAll, @beforeEach. . . )
- Singleton pattern: Balance class.
- Builder pattern: Made possible to reduce the constructor parameters of the Generator class.

## Interfaces

thhingsss

## Dependencies of the project
### Dependencies
- Truth: assertion library.
- Javafx.
- Telegram.

### Dev-dependencies
- spotless: code formatter.
- checkstyle: code linter.

## Our Experience
### Internal Organization
By being a very small team, we kept our organization flexible, without using any special working technique. We only wanted to try libraries, apply what we learned in the course and do the best we thought was useful for the project.
In a more advanced part of the development, we found a better division of the workload necessary to avoid conflicts. For example Francesco dedicated more time in the interfaces (when the main core was ready) while Alberto continued developing in the core adding loading and saving the game.
Each time we thought it was needed to prioritize different features, we divided jobs to do so, otherwise we only notified each other on which part we were working, based on we wanted to do for curiosity or because of specific ideas.

### Branch Strategy
Since the beginning, we approached the development on different branches, using a flexible policy but keeping the principle of having the working code on two branches: development and master.

- Master: containing only working code with at least a playable gameplay. We loaded here the very first version of the game, when it was only playable in CLI.
- Dev_main: containing the stable (but not release) code of the project, merged from side small feature branches when possible.
- Feature/XXXX: a generic example of the many branches we had (that we cleaned) where XXXX was the name of the feature.
- NrIssue-name-issue: another generic example of branches created in order to satisfy a specific issue (we started using them only at the end).

Close to the end of the project, where the code was starting to becoming more stable, trustable and documented, we started using gitlab issues, in order to keep the focus on the remaining features and track better the work.
From this, in the final days we extended the branching techniques to side branches that were opened directly from the issues (but in little part since the limited size of the project).

### Main Challenge Faced
Francesco: "I think I had two main challenges while developing the game. The first came up when I realised that the game needed to be more flexible and more adaptable to internal change adding another generator. The most difficult part of that was making the interface (especially javafx one) to adapt to a change like this or to the possibility to have an infinite number (not really infinite) of generators. The second came up instead when I started to see that the code I wrote was too difficult to be read, even by me. At that point, Alberto and I, started a quite long period of "housekeeping". We decide that the way the code is wrote is more important than the number of features actually implemented. Even if at first sight this part can seem easy and relaxing, I felt it to be much stressful because I lost much time thinking on how things should be refactored, trying to follow the most correct logical approach. What I mean is, for example, asking myself if a class should be a util class or maybe a singleton class or just a normal class, or another example trying to understand if some classes were too big and how decompose them in other subclasses (which need to be logically correct, sharing variables that were logical to be there together)".

Alberto:
"Should I restart the project today, I would use more the gitlab issues, which revealed to be very useful to prioritize the important features. In the project, I worked more on the "business logic", working especially on serialization and refactoring
Since I feel devtools to be an important part of projects, I tried to make practice with Maven, with some successes and a lot of failures.
Firstly, I was never able to make the code formatter and the linter run automatically at a linked Maven lifecycle. I feel this problem to have negatively impacted the code quality, because those tools didn't run at the specified stage, so we ended up with unformatted code, or worse, many spotbugs/checkstyle warnings to clean at the end of the project.
I also had problems with gitlab CI to deploy the Javadocs, those I could upload the correct artifacts but weren't hosted.
In this project there were also moments where we had to reconsider our decisions, which I felt crucial to adopt a reasoned approach to software development: in the beginning, we implemented the Observer pattern, but we felt it was introducing unnecessary complexity. Another time, we tried to make a singleton class deserialize data before the beginning of the game. After being stuck for a while, we decided that was not necessary and could have broken the thread resource sharing, so we avoided using it even we liked this design pattern".



## Next Steps

What could we add in the future versions:

- [ ] Implement the concept of derivative currencies (secondary currencies that can be exchanged with the primary and gainable from different type of generators).
- [ ] Add random events during the game experience.
- [ ] Add minigames which allow to bet using the primary currency, risking to lose it or gain more of it.

## Youtube Video

Link: (https://www.youtube.com/watch?v=p3bSIx2WK3s)[https://www.youtube.com/watch?v=p3bSIx2WK3s]


## References

1. A. Pecorella. (2016). The Math of Idle Games, Part I, II, III. [online] Available at: https://blog.kongregate.com/the-math-of-idle-games-part-i/ [Accessed 13 Jun. 2021].
2. J. Bloch. (2017). Effective Java: Programming Language Guide, third edition.



## License

Chose a license.
