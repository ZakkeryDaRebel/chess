# ♕ BYU CS 240 Chess

This project demonstrates mastery of proper software design, client/server architecture, networking using HTTP and WebSocket, database persistence, unit testing, serialization, and security.

## 10k Architecture Overview

The application implements a multiplayer chess server and a command line chess client.

[![Sequence Diagram](10k-architecture.png)](https://sequencediagram.org/index.html#initialData=C4S2BsFMAIGEAtIGckCh0AcCGAnUBjEbAO2DnBElIEZVs8RCSzYKrgAmO3AorU6AGVIOAG4jUAEyzAsAIyxIYAERnzFkdKgrFIuaKlaUa0ALQA+ISPE4AXNABWAexDFoAcywBbTcLEizS1VZBSVbbVc9HGgnADNYiN19QzZSDkCrfztHFzdPH1Q-Gwzg9TDEqJj4iuSjdmoMopF7LywAaxgvJ3FC6wCLaFLQyHCdSriEseSm6NMBurT7AFcMaWAYOSdcSRTjTka+7NaO6C6emZK1YdHI-Qma6N6ss3nU4Gpl1ZkNrZwdhfeByy9hwyBA7mIT2KAyGGhuSWi9wuc0sAI49nyMG6ElQQA)

## Modules

The application has three modules.

- **Client**: The command line program used to play a game of chess over the network.
- **Server**: The command line program that listens for network requests from the client and manages users and games.
- **Shared**: Code that is used by both the client and the server. This includes the rules of chess and tracking the state of a game.

## Starter Code

As you create your chess application you will move through specific phases of development. This starts with implementing the moves of chess and finishes with sending game moves over the network between your client and server. You will start each phase by copying course provided [starter-code](starter-code/) for that phase into the source code of the project. Do not copy a phases' starter code before you are ready to begin work on that phase.

## IntelliJ Support

Open the project directory in IntelliJ in order to develop, run, and debug your code using an IDE.

## Maven Support

You can use the following commands to build, test, package, and run your code.

| Command                    | Description                                     |
| -------------------------- | ----------------------------------------------- |
| `mvn compile`              | Builds the code                                 |
| `mvn package`              | Run the tests and build an Uber jar file        |
| `mvn package -DskipTests`  | Build an Uber jar file                          |
| `mvn install`              | Installs the packages into the local repository |
| `mvn test`                 | Run all the tests                               |
| `mvn -pl shared test`      | Run all the shared tests                        |
| `mvn -pl client exec:java` | Build and run the client `Main`                 |
| `mvn -pl server exec:java` | Build and run the server `Main`                 |

These commands are configured by the `pom.xml` (Project Object Model) files. There is a POM file in the root of the project, and one in each of the modules. The root POM defines any global dependencies and references the module POM files.

## Running the program using Java

Once you have compiled your project into an uber jar, you can execute it with the following command.

```sh
java -jar client/target/client-jar-with-dependencies.jar

♕ 240 Chess Client: chess.ChessPiece@7852e922
```


## Phase 2 Sequence Diagram URL
https://sequencediagram.org/index.html?presentationMode=readOnly#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWDABLBoAmCtu+hx7ZhWqEUdPo0EwAIsDDAAgiBAoAzqswc5wAEbBVKGBx2ZMAcygQArthgBiNMCoBPGACUUZpKrBQ5SCDR7AHcACyQwMURUUgBaAD5yShooAC4YAG0ABQB5MgAVAF0YAHorAygAHTQAb3LKRwBbFAAaGFx1YOgONpRG4CQEAF9MYRSYBNZ2Lkp0uoqm1vb9VS6oHpg+geHMNk5uWEmx0XSoT29IqAAKDzM1S48ARyt7gEpR5NEJxNl5JRV1Ok7mAAKoVK71KCLd6-RTKNSqb5GHTpMgAUQAMmi4PkYJDFjAAGaWRp4iqYWH-BETI6fFTpNBWBAID4iFRIynwwEwEBnOQoMGUCELYDNNodVbdXr9QYw7RU9RI4zpACSADl0W5cfjRUsJWsNltBjB1fkcmTaMd2ZNOQDVOleSh+QorGBQsKGrq5X8uYjJsqTRq0VqLVDdW1gK7QvkIABrdCBs0wSNuiny300xJWlDpFPRuPoVlUL6xSZ7GZpdznHyUDyqJlYcsHTNJaizGDzT1i5NRmPxtAjMaUFvRdBgdIAJgADFPqp2w928330CN0BxTBZrLY7NB2HcYBiIF4gnYwhEosgx0ih5XsnkiqUDOoAmg5zru-ruoPksOy9MDnM756isBojE2v5ZnSOYwAgR5IGgVyHseTwvD47zZhy6Z2kCKCguCQHenCdpKii5CYti2ois0RIkqGRYlj8WEIg6fKRC6boeguKCEQqfqJAG6qapRXZLEuBZBKa5p5mmPrEaWkFstBYn9vR1p-vs7ZIfBdYNrs-6-rSbaVvOiwRr24nfkZI6Xhgk4zm+VGieZ-arpwG6WDY9gWCgCZIdYzCnuEkSYKOcQKSk6QZNI5H5GixQlE+qgvtUymFjeSLgcZqUDnpGmHOFJwwf5-mIUe-koW8qmGDaTHchwKDcJEVzsaEPEZv6pFuGiACyOQAGpoj2bo0RApLSba1Lya2xb0lVLaZekfmujpCCNvphyGRFRYGYkoXjjA06zrUrnruYHnbmcHAHhcMAAOK6oigXniFNnMJt7YZLdaIPiUZi6ilzlpT++VTHlczZWB63XlBOFgPdzSqIhFzw2oFVoXNNWycxMDAi1VzZW1cn8aR6JYjiQ2hCNY1RhjjFY9yyA+CjiOE5NxOouR5N-c0GI3cSo047qMlEZNm2FdzaiZBLKrSG0Z6RIKXFtDoCCgLGiumYLzRqrqhQY+pFaLcjD0rWteUtjeQIPVLuoy3LQUCo5yuqyA6tO1rKA680evpVNe12UdNQS4iGTS7LMDy47InO2rGvhh7XsoIUJ3uVu9jYFYUDYNw8CsYYKMhA7L0xG94UfbkBTxRLANusur61BLieWeMBsAR2je6pD5ti-SPJ5yjVxwP3upo2A6FQZh9P2jjuF4wTwu8SRHNk7ieZUxTtMyLV0-AgPHfNKziodcvFEJ7q68Swv7U99BjLMpvE3co6-J77qieH3xyKqkGIb74YkkeyvnJG+Vtmgy31okBaucnSRBRqbXKFYLbAzmGHZuO14CvQDnOVBmA1yp08nYZwDVYLBBgAAKQgPBO6587AqzVsXK871bxZBBD9aujhAb1xqNnYARCoBwAgLBKAbQcG+0getcGnDxSqz4QIoRIjbbSC7ogkBMAABWlC0ADwofBOBKBniVQwpjEW3JcZRnxpwj+S8yIrwpuvaSRi6YmJ3rhRWFja7iSscfGxp8gL2Jpo4reU9YavzAdILx7MfFc0URfIWgTH4uLAFkGRlAABiJI5HQCuDw2RgjoARK-lE3EOTKCZNgPzUkl8ElIMUgyJkLJ4nb3SAYMAAB1B2idOLvxgDkNwMAWkACEXaxk6ZCd+QC2aFMEsGYSUBE6JnNFU7eNSZo5ggaDQ25DNF6PrKtBBzYpqW22htXamCDr2WOrgtyZ0052AsLwjBTpYDAGwNnQg-hAiF2entaGRlIrRSxLFeKxhjkZQkR2ZRByCq9xANwPAg84VQAUMyUe49FKT2cekeqjUUDIoQC1FmEyj6RK6r1AayZmQU0RBUjejTglGAarhXFzJFaEuqd40l-VBrAEpZCaltFIQPyaQynFeLmZXAKQGTl5KeUIA9vygWSyp4rJOOsha+z0FHN9pMf25zA4pyAA