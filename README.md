# Jubble🚀
A "spacial" multithreading incremental game you can play via javafx or via a telegram bot.

Run Javafx app.
````
mvn clean compile exec:java@javafx
````

Run telegram bot.
````
mvn clean compile exec:java@telegram
````

Build project and verify code style
````
mvn clean verify
````

Generate code style report
````
mvn checkstyle:check
````

Temporal command for formatting:
````
mvn com.coveo:fmt-maven-plugin:format
````

## References
1. A. Pecorella. (2016). The Math of Idle Games, Part I, II, III. [online] Available at: https://blog.kongregate.com/the-math-of-idle-games-part-i/ [Accessed 13 Jun. 2021].

## Next Steps
What could we add in the future versions:
- [ ] Implement the concept of derivative currencies.
- [ ] Add random events during the game experience.
