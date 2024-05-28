package service;

import dataaccess.DataBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.CreateGameRequest;
import request.RegisterRequest;
import result.CreateGameResult;
import result.RegisterResult;

public class CreateGameTests {

    private GameService gameService;
    private CreateGameResult gameResult;
    private String authToken;

    @BeforeEach
    public void createDataBase() {
        DataBase db = new DataBase();
        UserService userService = new UserService(db);
        RegisterResult registerResult = userService.createUser(new RegisterRequest("Test", "1234", "test@gmail.com"));
        authToken = registerResult.getAuthToken();
        gameService = new GameService(db);
    }

    @Test
    public void createNewGame() {
        gameResult = gameService.createGame(new CreateGameRequest(authToken, "Test"));
        Assertions.assertTrue(gameResult.isSuccess(), gameResult.getMessage());
    }

    @Test
    public void createExistingGame() {
        gameResult = gameService.createGame(new CreateGameRequest(authToken, "Test"));
        Assertions.assertTrue(gameResult.isSuccess(), gameResult.getMessage());
        gameResult = gameService.createGame(new CreateGameRequest(authToken, "Test"));
        Assertions.assertFalse(gameResult.isSuccess(), gameResult.getMessage());
    }

    @Test
    public void createGameInvalidAuth() {
        gameResult = gameService.createGame(new CreateGameRequest("1234", "Test"));
        Assertions.assertFalse(gameResult.isSuccess(), gameResult.getMessage());
    }
}
