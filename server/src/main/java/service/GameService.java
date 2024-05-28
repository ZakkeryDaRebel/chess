package service;

import dataaccess.*;
import model.*;
import request.*;
import result.*;

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
    public JoinGameResult joinGame(JoinGameRequest request) {
        String authToken = request.getAuthToken();
        JoinGameResult result;
        try {
            AuthData auth = database.getAuth(authToken);
            GameData game = database.getGame(request.getGameID());
            GameData newGame;
            Boolean spectator = false;
             if(request.getPlayerColor().equalsIgnoreCase("WHITE")) {
                 if(database.getPlayerFromColor(game, "WHITE") != null)
                     throw new DataAccessException("Error: already taken");
                 newGame = new GameData(game.gameID(), auth.username(), game.blackUsername(), game.gameName(), game.game());
                 database.updateGame(newGame);
             } else if (request.getPlayerColor().equalsIgnoreCase("BLACK")) {
                if(database.getPlayerFromColor(game, "BLACK") != null)
                    throw new DataAccessException("Error: already taken");
                newGame = new GameData(game.gameID(), game.whiteUsername(), auth.username(), game.gameName(), game.game());
                 database.updateGame(newGame);
             }
            result = new JoinGameResult(true, null);
        } catch(DataAccessException ex) {
            result = new JoinGameResult(false, ex.getMessage());
        }
        return result;
    }

    //List Games
    public ListGamesResult listGames(ListGamesRequest request) {
        String authToken = request.getAuthToken();
        ListGamesResult result;
        try {
            database.getAuth(authToken);
            ArrayList<GameData> gameList = database.getGameList();
            for(int i = 0; i < gameList.size(); i++) {
                boolean nullData = false;
                GameData game = gameList.get(i);
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

                gameList.set(i, new GameData(game.gameID(), whiteUsername, blackUsername, game.gameName(), null));
            }
            result = new ListGamesResult (true, null, gameList);
        } catch(DataAccessException ex) {
            result = new ListGamesResult(false, ex.getMessage(), null);
        }
        return result;
    }

    //Delete Game
}
