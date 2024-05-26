package service;

import dataaccess.DataBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.*;
import result.*;

public class ListGamesTests {

    private GameService gameService;
    private CreateGameResult createResult;
    private ListGamesResult listResult;
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

    //Create1GameThenList
    @Test
    public void createOneGameThenList() {
        createResult = gameService.createGame(new CreateGameRequest(authToken, "Test"));
        Assertions.assertTrue(createResult.isSuccess(), createResult.getMessage());
        listResult = gameService.listGames(new ListGamesRequest(authToken));
        Assertions.assertTrue(listResult.isSuccess(), listResult.getMessage());
    }

    @Test
    public void createTwoGamesThenList() {
        createResult = gameService.createGame(new CreateGameRequest(authToken, "Test"));
        Assertions.assertTrue(createResult.isSuccess(), createResult.getMessage());
        createResult = gameService.createGame(new CreateGameRequest(authToken, "Test2"));
        Assertions.assertTrue(createResult.isSuccess(), createResult.getMessage());
        listResult = gameService.listGames(new ListGamesRequest(authToken));
        Assertions.assertTrue(listResult.isSuccess(), listResult.getMessage());
    }

    @Test
    public void listWithNoGames() {
        listResult = gameService.listGames(new ListGamesRequest(authToken));
        Assertions.assertTrue(listResult.isSuccess(), listResult.getMessage());
    }
}
