package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.*;
import result.*;

public class ListGamesTests extends ParentTests {

    private CreateGameResult createResult;
    private ListGamesResult listResult;

    @BeforeEach
    public void setUp() {
        createDataBase();
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
