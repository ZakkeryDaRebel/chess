package service;

import dataaccess.DataBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.*;
import result.*;

public class JoinGameTests {

    private GameService gameService;
    private CreateGameResult createResult;
    private JoinGameResult joinResult;
    private UserService userService;
    private RegisterResult registerResult;
    private String authToken;

    @BeforeEach
    public void createDataBase() {
        DataBase db = new DataBase();
        userService = new UserService(db);
        registerResult = userService.createUser(new RegisterRequest("Test", "1234", "test@gmail.com"));
        authToken = registerResult.getAuthToken();
        gameService = new GameService(db);
    }

    @Test
    public void createGameAndJoinWHITE() {
        createResult = gameService.createGame(new CreateGameRequest(authToken, "NEWGAME!"));
        Assertions.assertTrue(createResult.isSuccess(), createResult.getMessage());
        joinResult = gameService.joinGame(new JoinGameRequest(authToken, "WHITE", createResult.getGameID()));
        Assertions.assertTrue(joinResult.isSuccess(), joinResult.getMessage());
    }

    @Test
    public void createGameAndJoinBLACK() {
        createResult = gameService.createGame(new CreateGameRequest(authToken, "NEWGAME!"));
        Assertions.assertTrue(createResult.isSuccess(), createResult.getMessage());
        joinResult = gameService.joinGame(new JoinGameRequest(authToken, "BLACK", createResult.getGameID()));
        Assertions.assertTrue(joinResult.isSuccess(), joinResult.getMessage());
    }

    @Test
    public void noCreateTryJoin() {
        joinResult = gameService.joinGame(new JoinGameRequest(authToken, "WHITE", 10));
        Assertions.assertFalse(joinResult.isSuccess(), joinResult.getMessage());
    }

    @Test
    public void invalidAuth() {
        createResult = gameService.createGame(new CreateGameRequest(authToken, "Test"));
        Assertions.assertTrue(createResult.isSuccess(), createResult.getMessage());
        joinResult = gameService.joinGame(new JoinGameRequest("1234", "WHITE", createResult.getGameID()));
        Assertions.assertFalse(joinResult.isSuccess(), joinResult.getMessage());
    }

    //TeamAlreadyTaken
    @Test
    public void colorAlreadyTaken() {
        createResult = gameService.createGame(new CreateGameRequest(authToken, "Fun Game"));
        Assertions.assertTrue(createResult.isSuccess(), createResult.getMessage());
        joinResult = gameService.joinGame(new JoinGameRequest(authToken, "BLACK", createResult.getGameID()));
        Assertions.assertTrue(joinResult.isSuccess(), joinResult.getMessage());
        registerResult = userService.createUser(new RegisterRequest("NewGuy", "9081726354", "lol@gmail.com"));
        Assertions.assertTrue(registerResult.isSuccess(), registerResult.getMessage());
        String newToken = registerResult.getAuthToken();
        joinResult = gameService.joinGame(new JoinGameRequest(newToken, "BLACK", createResult.getGameID()));
        Assertions.assertFalse(joinResult.isSuccess(), joinResult.getMessage());
    }

    @Test
    public void createGameAddBoth() {
        createResult = gameService.createGame(new CreateGameRequest(authToken, "Full Game, Let's PLAY"));
        Assertions.assertTrue(createResult.isSuccess(), createResult.getMessage());
        joinResult = gameService.joinGame(new JoinGameRequest(authToken, "BLACK", createResult.getGameID()));
        Assertions.assertTrue(joinResult.isSuccess(), joinResult.getMessage());
        registerResult = userService.createUser(new RegisterRequest("NewGuy", "9081726354", "lol@gmail.com"));
        Assertions.assertTrue(registerResult.isSuccess(), registerResult.getMessage());
        String newToken = registerResult.getAuthToken();
        joinResult = gameService.joinGame(new JoinGameRequest(newToken, "WHITE", createResult.getGameID()));
        Assertions.assertTrue(joinResult.isSuccess(), joinResult.getMessage());
    }
}
