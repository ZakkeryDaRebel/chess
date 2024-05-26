package service;

import dataaccess.DataAccessException;
import dataaccess.DataBase;
import model.GameData;
import request.CreateGameRequest;
import result.CreateGameResult;

public class GameService {

    DataBase database;

    public GameService(DataBase database) {
        this.database = database;
    }

    //Create Game
    public CreateGameResult createGame(CreateGameRequest request) {
        String authToken = request.getAuthToken();
        String gameName = request.getGameName();
        CreateGameResult result;
        try {
            database.getAuth(authToken);
            if(!database.noGameName(gameName))
                throw new DataAccessException("Game already Exists");
            database.createGame(gameName);
            GameData game = database.getGameName(gameName);
            result = new CreateGameResult(true, null, game.gameID());
        } catch(DataAccessException ex) {
            result = new CreateGameResult(false, "Error: " + ex.getMessage(), null);
        }
        return result;
    }

    //Join Game

    //List Games

    //Delete Game
}
