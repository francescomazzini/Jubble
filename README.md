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
To play from the telegram client go on [@jubble_bot](https://t.me/jubble_bot) on telegram.

```
mvn clean compile exec:java@telegram
```

Clean project.

```
mvn clean
```

Run project tests and generate coverage report (then you can find it at /target/site/jacoco/index.html).

```
mvn test
```

Generate executable Jars (one for JAVAFX, another for TELEGRAM).

```
mvn package
```

Generate JAVADOC (then you can find it at /target/site/apidocs/index.html).

```
mvn javadoc:javadoc
```

Generate code style report (the errors reported are suggestions and should not be considered as the only as absolute truth)

```
mvn checkstyle:check
```


## Gameplay

In Jubble, a little Astronaut landed on a planet after having run out of fuel.
This planet is mostly inhabited, and no one can help. At the beginning he/she only has a little stellar panel, which produces some Energy needed to come back home, but is obviously not enough to make his rocket able to travel for millions of light years in the space.
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

This is how we compute the next cost of a generator:
$`cost_{next} = cost_{base} \times (rate_{growth})^{owned}`$

This is how the total production per second is computed:
$`production_{total} = \displaystyle \sum_{i=0}^{generators} (production_{base_i} \times owned_i)`$

## Code Structure:

In brief, the code is splitted in three folders:
- core
- javafx
- telegram

The core is the core logic of the game and constitutes an api that can be accessed to the clients.  
  
Java and Telegram have different entrypoint, and are based on the "core". They can be run using the command specified above.
  
![Scheme](https://i.imgur.com/lBHDLUB.png)  

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

## User Interfaces

### JavaFX
JavaFX provides a graphics user interface of the game. To realize it we used SceneBuilder to generate a main structure so that was summarized in a unique fxml and the other things were instead created through code. In this way the graphic tends to adapt to the program as more as possible. Thanks to this "expandibility" feature it is much simple to make the number of generators being more or less (it is only needed to modify the Settings class adding more Generators, to add a new image for that generator and the job it is done! Remember that you need to restart the game from zero because this is a development edit, so you need to delete your game_progress.json and start the game! We have done this also in the youtube video presentation that you can find below).

#### Explanation of Some Useful Buttons:  
Left Arrow Button: skips to the next page (if there are enough generators to need more than 1 page)  
Right Arrow Button: skips to the previous page (if you changed page)   
Shop Button: open the pop up of the scrollable shop. There it is possible to buy generators  


### Telegram
The telegram bot doesn't need a configuration, because we have already prepared a Token linked to a bot named "[@jubble_bot](https://t.me/jubble_bot)".
However, if you would prefer to have your personal bot which use our code, you could create a new bot on telegram and you could use your token and your bot name replacing ours [in this class](src/main/java/com/jubble/app/telegram/BotConstants.java).

#### Explanation of Some Useful Buttons:  
Begin: starts the game from the client  
Shop: opens the shop  
Status: opens a report of the status of the resources (generators and balance)  
Back: goes to the previous message  
Stop: stops the game in order to make the progress saved  (this has to be run before of stopping the server!)  
 
## Tests

To cover more code as possible we used a plugin called "Jacoco" which after the maven command "mvn test" returns a report which contains the test coverage. However we want to specify that we wanted to test only the core of the app (excluding the user interfaces). We only tested a little part in javaFx where we had Tasks which are graphical threads and we just wanted to be sure that we set them in the correct way.  
  
In the core instead, almost everythings is covered. What it remains are basic methods and testing them would be useless (like basic getter and setter) and also a part of threads. That part is not covered because we have special exception in case someone tries to set number of generators once the App is started. Therefore tests would work only if they are run lonely, which does not make sense, so we tested that code copying it in another test (which mainly check the job of threads and timer) and without setting number of generators. In this way it is covered but not directly and not specificly, but it is not even much needed because we already tested de/serialization.  

## Pipeline
We set up a continous integration system to validate the code after each push on Gitlab. We defined three stages:  
- build: builds the project.  
- test: runs test actions.  
- deploy: deploys Javadoc to artifacts. The intention was to deploy the Javadoc on Gitlab pages, but we couldn't enable this feature  

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

Link: https://www.youtube.com/watch?v=p3bSIx2WK3s


## References

1. A. Pecorella. (2016). The Math of Idle Games, Part I, II, III. [online] Available at: https://blog.kongregate.com/the-math-of-idle-games-part-i/ [Accessed 13 Jun. 2021].
2. J. Bloch. (2017). Effective Java: Programming Language Guide, third edition.



## License

Chose a license.
