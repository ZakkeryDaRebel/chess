package service;

import dataaccess.DataAccessException;
import dataaccess.DataBase;
import model.GameData;
import request.CreateGameRequest;
import request.ListGamesRequest;
import result.CreateGameResult;
import result.ListGamesResult;

import java.util.ArrayList;

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
    public ListGamesResult listGames(ListGamesRequest request) {
        String authToken = request.getAuthToken();
        ListGamesResult result;
        try {
            database.getAuth(authToken);
            ArrayList<GameData> gameList = database.getGameList();
            for(GameData game : gameList) {
                boolean nullData = false;
                String whiteUsername;
                String blackUsername;
                if(game.blackUsername() == null) {
                    nullData = true;
                    blackUsername = "";
                } else
                    blackUsername = game.blackUsername();
                if(game.whiteUsername() == null) {
                    nullData = true;
                    whiteUsername = "";
                } else
                    whiteUsername = game.whiteUsername();
                game = new GameData(game.gameID(), whiteUsername, blackUsername, game.gameName(), game.game());
            }
            result = new ListGamesResult (true, null, gameList);
        } catch(DataAccessException ex) {
            result = new ListGamesResult(false, ex.getMessage(), null);
        }
        return result;
    }

    //Delete Game
}
