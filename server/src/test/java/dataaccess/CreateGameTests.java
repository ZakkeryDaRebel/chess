package dataaccess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.CreateGameRequest;
import result.CreateGameResult;

public class CreateGameTests extends ParentTests {

    CreateGameResult gameResult;

    @BeforeEach
    public void setUp() {
        createDataBase();
        clearService.deleteAll();
        createUser();
    }

    @Test
    public void createNewGame() {
        gameResult = gameService.createGame(new CreateGameRequest(authToken, "Test"));
        Assertions.assertTrue(gameResult.isSuccess(), gameResult.getMessage());
    }

    @Test
    public void createExistingGame() {
        createNewGame();
        gameResult = gameService.createGame(new CreateGameRequest(authToken, "Test"));
        Assertions.assertFalse(gameResult.isSuccess(), gameResult.getMessage());
    }

    @Test
    public void createGameInvalidAuth() {
        gameResult = gameService.createGame(new CreateGameRequest("1234", "Test"));
        Assertions.assertFalse(gameResult.isSuccess(), gameResult.getMessage());
    }
}
