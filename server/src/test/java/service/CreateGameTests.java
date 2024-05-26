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
    public void CreateNewGame() {
        gameResult = gameService.createGame(new CreateGameRequest(authToken, "Test"));
        Assertions.assertTrue(gameResult.isSuccess(), gameResult.getMessage());

    }

    @Test
    public void CreateExistingGame() {
        gameResult = gameService.createGame(new CreateGameRequest(authToken, "Test"));
        Assertions.assertTrue(gameResult.isSuccess(), gameResult.getMessage());
        gameResult = gameService.createGame(new CreateGameRequest(authToken, "Test"));
        Assertions.assertFalse(gameResult.isSuccess(), gameResult.getMessage());
    }

    @Test
    public void CreateGameInvalidAuth() {
        gameResult = gameService.createGame(new CreateGameRequest("1234", "Test"));
        Assertions.assertFalse(gameResult.isSuccess(), gameResult.getMessage());
    }
}
