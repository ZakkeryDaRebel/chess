package handler;

import dataaccess.DataAccessException;
import dataaccess.DataBase;
import model.AuthData;
import model.GameData;
import request.JoinGameRequest;
import result.JoinGameResult;
import service.GameService;
import spark.Request;
import spark.Response;
import spark.Route;

public class JoinGameHandler implements Route {

    DataBase database;
    HandlerMethods handlerMethods;

    public JoinGameHandler(DataBase database) {
        this.database = database;
        handlerMethods = new HandlerMethods();
    }

    @Override
    public Object handle(Request request, Response response) {
        JoinGameRequest joinRequest;
        String token;
        try {
            joinRequest = (JoinGameRequest) handlerMethods.getBody(request, "JoinGameRequest");
            handlerMethods.isNullString(joinRequest.getPlayerColor());
            handlerMethods.isNullInteger(joinRequest.getGameID());
            token = handlerMethods.getAuthorization(request);
        } catch(DataAccessException ex) {
            return handlerMethods.getResponse(response,400, new JoinGameResult(null, "Error: bad request"));
        }
        AuthData auth;
        try {
            handlerMethods.isNullString(token);
            database.getAuth(token);
            joinRequest.setAuthToken(token);
            auth = database.getAuth(token);
        } catch(DataAccessException ex) {
            return handlerMethods.getResponse(response, 401, new JoinGameResult(null, "Error: unauthorized"));
        }
        try {
            GameData game = database.getGame(joinRequest.getGameID());
            String playerColor = joinRequest.getPlayerColor();
            if(playerColor.equalsIgnoreCase("WHITE")) {
                if(database.getPlayerFromColor(game, "WHITE") != null)
                    throw new DataAccessException("Error: already taken");
            } else if(playerColor.equalsIgnoreCase("BLACK")) {
                if(database.getPlayerFromColor(game, "BLACK") != null)
                    throw new DataAccessException("Error: already taken");
            }
        } catch(DataAccessException ex) {
            return handlerMethods.getResponse(response, 403, new JoinGameResult(null, "Error: already taken"));
        }
        GameService joinGame = new GameService(database);
        JoinGameResult joinGameResult = joinGame.joinGame(joinRequest);
        if(joinGameResult.isSuccess()) {
            return handlerMethods.getResponse(response, 200, joinGameResult);
        } else {
            //Return error with the message
            return handlerMethods.getResponse(response, 500, joinGameResult);
        }
    }

}
