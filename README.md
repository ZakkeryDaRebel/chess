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
https://sequencediagram.org/index.html?presentationMode=readOnly#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWOZVYSnfoccKQCLAwwAIIgQKAM4TMAE0HAARsAkoYMhZkwBzKBACu2GAGI0wKgE8YAJRRakEsFEFIIaYwHcAFkjAdEqUgBaAD4WakoALhgAbQAFAHkyABUAXRgAej0VKAAdNABvLMpTAFsUABoYXCl3aBlKlBLgJAQAX0wKcNgQsLZxKKhbe18oAAoiqFKKquUJWqh6mEbmhABKDtZ2GB6BIVFxKSitFDAAVWzx7Kn13ZExSQlt0PUosgBRABk3uCSYCamYAAzXQlP7ZTC3fYPbY9Tp9FBRNB6BAIDbULY7eRQw4wECDQQoc6US7FYBlSrVOZ1BpNFo3LH3KRPNQKKIASQAcu8rL9-mTppT5otli0YJykvEwbQ4RjQpDGRIoniUAThHowJ4SZN+fS9grmS8xVy3jypdryTBgOrPEkIABrdBGiWW60QhkHR6BWGbfoujW2h1oNHwmGhOGRP02+3oYOUUPwZDoMBRABMAAY03lCld+ZUrf7o2h2ugZJodPpDEZoLxjjAPhA7G4jF4fH5E0Ew6wI3FEqkMiopC40Fm+RbBXV2uHup30b6EA2kGgtVMKbMhesZeJmfKPUcTkSxqOULq7h6DazyJ9vryc2UgSCzcHZTAdw8lfjfGqNcudW69WeekNTluRvUkLXzKNAydSUIL-U9oS9Gd4SiCCAxjTdVEQ3oI1QwtY26Hp-CTVMMxHW9plwwNizQUttF0AxjB0FBHXrLR9GYZtvF8TAiI7bCoCiaI+CvJI3jSdIBwkIc8ko9Cu2nXp2Ciec2PVUZZLQDcfUwuV3TfNQUAQE4UFGL9PBPbFPWeC8rDeABZeIADU3kje8IFBWDXyZLCMIiJ8tx8+Sonw+NeOTGB00zApqNo8sGKMQYZDrYYYAAcX5R5ONbHj22Yb0ukE1K3j7dItH5GTrTQoMp2ZXyYGOMB0rKCR1MqwstNnHSXz0nEGrM1qC0DCz9UAi93i+H5XOBdzI38rqvMVGBkAcJrJFGYaAOs14r0msqyg+FLptBPaOAW+M6pOx5ohOtk+EqFtfAPFcYAUBBQDtJ7c3q-kOX5FI5vOoLvuamIbrumAHsJcjKle97PotE7frKf6aqwsKSKi-JLtB-lbvuriobA6ZYZAD7oeBlAkZQFIYrLejK2wPQoGwIz4A-VRVo8AmcoCPKkO7BJknEk6KsG9As0R-lJ3k2rtPfFVfFWlqNMqSWyg6kNMX-fS+utAbIPQDaEK2y8Jt+CC3I810MO3HrFoa1bRjV484Ms89trNimqctinXZG-LkJgJEUQBrX4JxZUCUd52je8k3gJNX5neg32zsCzqjlxvhQ4DiMwZCtHcoxiWs9puiK2McxDPndwYAAKQgRc0v5YwSbtHmk1lgqYliU4SpF0w2sDLMWeAKuoDgCB5ygVXS4LpClJgAArRu0EdlWqje8fJ+n2eylujXnwWvcwH6jTY6slkPevKaH1gm2w8sk-o6zi-3dNm+wZ9k7Q907XepOLELelAABiIId7QFGKPbeU9oBv1GtfSa0DKAQNgEdVOdtAYZyDsiVED8-7h0WioMAQCx6gPAbAsYyCJ6ULzEPQ2ftNpXyNCBTeZCaG70jFVFOP804ByUjnfmAkC6ESLhFUi0VMAljphXIwOgx4JhVLAYA2AWaEGcK4Lm2UwpdxoAJGIwkviiXEuoeeilfQgCMngdav9ur-0WjIQyxlhAojMi1eBJtbIOWcpaFEkZHjoPvtpW29ioiOKMp+FEB53GMONswrxTkXLAD8RMAJD4Ji2OPgZCJKAXEICVjYtOnj7KJN8QgCmaSZq8MwenZCgj+J+VRqI3mxdJHSKAA