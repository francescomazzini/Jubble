Run project
````
mvn clean compile exec:java
````

Build project and verify code style
````
 mvn clean verify
````

Generate code style report
````
mvn checkstyle:check
````

Observer pattern, please note:
java.util.Observable

Deprecated.
This class and the Observer interface have been deprecated. The event model supported by Observer and Observable is quite limited, the order of notifications delivered by Observable is unspecified, and state changes are not in one-for-one correspondence with notifications. For a richer event model, consider using the java.beans package. For reliable and ordered messaging among threads, consider using one of the concurrent data structures in the java.util.concurrent package. For reactive streams style programming, see the Flow API.


## References
[1. Multithreading observer pattern in Java](https://www.techyourchance.com/thread-safe-observer-design-pattern-in-java/)

## Next Steps
( [] ) Add Serialization and Deserialization to save and load the game
( [] ) Add a first graphical user interface with javafx
( [] ) Continue the setup of the game adding more currencies, generators and multipliers
( [] ) Add random events during the game experience

