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

In Jubble, a little Astronaut landed on a planet after having run out of fuel. This planet is mostly inhabitated, at the beginnning he (or she) only has a little stellar panel to gain some Energy to back home. But this is obviously not enough to repair and make his rocket able to travel for millions of light years in the space. More and more energy is needed in order to do so! In the shop, the player can also buy using Energy more generators with different production rate!

The endless goal is to reach as more energy as the player can. Depending on how the player will buy generators, his production will be different, being more or less efficient!

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

In brief, the code is divided in three folders:
- core
- javafx
- telegram

The last two, are independent from each other, and are based on the "core". They can be run using the command specified above. 

The telegram bot doesn't need a configuration, since we have already prepared a Token linked to a bot named jubble_bot. 
However, since sharing bot tokens is specifically discouraged by the bot library, should this game become very successfull and someone decide to self host it, we reccomend to replace that token with a personal one.  

A detailed representation of the code structure can be found in the generated Javadoc.

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
By being a very small team, we felt not necessary at the beginning to lay down any special organization between us. We only wanted to try libraries, discover new techniques and do the best we thought it could have been useful for the project.  

In a more advanced part of the development, we found a better division of the workload necessary to avoid conflicts. For example Francesco dedicated more time in the interfaces (when the main core was ready) while Alberto continued developing in the core adding loading and saving the game. Always with a flexible approach, each time we thought it was needed to prioritize different features, we divided jobs to do so, otherwise we only notified each other on which part we were working, based on we wanted to do for curiosity or because of specific ideas.

### Branch Strategy
Since the beginning, we approached the development on different branches, using a flexible policy but keeping the principle of having the working code on two branches.

- Master: containing only working code with at least a playable gameplay. We loaded here the very first version of the game, when it was only playable in CLI.

- Dev_main: containing the stable code of the project, merged from side branches created for every small feature when possible.

- Feature/XXXX: a generic example of the many branches we had (that we cleaned) where XXXX was the name of the feature.

- NrIssue-name-issue: another generic example of branches created in order to satisfy a specific issue (we started using them only at the end).

Close to the end of the project, where the code was starting to becoming more stable, trustable and documented, we started using gitlab issues, in order to keep the focus on the remaining features and track better the work.
From this, in the final days we extended the branching techniques to side branches that were opened directly from the issues (but in little part since the limited size of the project).

### Main Challenge Faced

Francesco: "I think I had two main challenges while developing the game. The first came up when I realised that the game needed to be more fexible and more adaptable to internal change adding another generator. The most difficult part of that was making the interface (especially javafx one) to adapt to a change like this or to the possibility to have an infinite number (not really infinite) of generators. The second came up instead when I started to see that the code I wrote was too difficult to be read, even by me. At that point, Alberto and I, started a quite long period of cleaning code. We decide that the way the code is wrote is more important than the number of features actually implemented. Even if this part can seem easy and relaxing, it is not and it is also much stressful because I lost much time thinking on how things should be done, trying to follow the most correct logical approach. What I mean is, for example, asking myself if a class should be a util class or maybe a singleton class or just a normal class, or another example trying to understand if some classes were too big and how decompos them in other subclasses (which need to be logically correct, sharing variables that were logical to be there together)"

Alberto:
"Should I restart the project today, I would use more the gitlab issues, which have revealed themselves to be very useful to prioritize the important features. In the project, I worked more on the "business logic", working especially on serialization and refactoring 
Since I feel devtools to be an important part of projects, I tried to make practice with Maven, with some successes and a lot of failures.
Firstly, I was never able to make the codeformatter and the linter run automathically at a linked lifecycle, but they never worked. I feel this problem to have partly negatively impacted the code quality, because those tools didn't run very often and we ended up with unformatted code and many spotbugs/checkstyle warnings to clean at the end of the project. I also had problems with gitlab CI to deploy the Javadocs, those I could upload the correct artifacts but weren't hosted.
In this project there were also moments where we had to reconsider our decisions, and I want to mention those memories since I feel to be useful to develop a critic approach to software development: in the beginning, we implemented the Observer pattern, but we felt it was introducing unnecessary complexity. Also when to make a singleton class deserialize data before the beginning of the game. After being stuck for a while, we decided that was not necessary and could have broken the thread resource sharing, due the fact that the deserialized reference would have been null".



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
