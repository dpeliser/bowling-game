Bowling 1.0.1
=============

Spring Boot command-line application to score a game of ten-pin bowling.

- [Rules](https://en.wikipedia.org/wiki/Ten-pin_bowling#Rules_of_play)
- [Scoring Bowling](Scoring Bowling)

## Build and Run

### Build

To build the application, run the following command inside the root folder of the project:

```
./gradlew bootJar
```

### Run

To run the application, use a command with the following format:

```
java -jar build/libs/JavaChallenge-1.0.1.jar PATH_TO_FILE
```

Where _PATH_TO_FILE_ is the path to a file with the Bowling moves. Examples using the files inside the `src/test/resources` folder:

```
java -jar build/libs/JavaChallenge-1.0.1.jar src/test/resources/positive/perfect.txt

java -jar build/libs/JavaChallenge-1.0.1.jar src/test/resources/positive/scores.txt

java -jar build/libs/JavaChallenge-1.0.1.jar src/test/resources/positive/zero.txt

java -jar build/libs/JavaChallenge-1.0.1.jar src/test/resources/positive/foul.txt

java -jar build/libs/JavaChallenge-1.0.1.jar src/test/resources/negative/empty.txt

java -jar build/libs/JavaChallenge-1.0.1.jar src/test/resources/negative/extra-score.txt

java -jar build/libs/JavaChallenge-1.0.1.jar src/test/resources/negative/free-text.txt

java -jar build/libs/JavaChallenge-1.0.1.jar src/test/resources/negative/invalid-score.txt

java -jar build/libs/JavaChallenge-1.0.1.jar src/test/resources/negative/negative.txt

java -jar build/libs/JavaChallenge-1.0.1.jar src/test/resources/negative/missing-score.txt
```

## About

I chose to use a Spring Boot application to show more use of web technologies, but usually I try to solve problems with 
the simplest solution to avoid using too many technologies or too complex technologies for simple problems.

## Authors

* **Diego Peliser** - *peliser.diego@gmail.com*