package handler;

import dataaccess.DataAccessException;
import dataaccess.DataBase;
import request.*;
import result.*;
import service.GameService;
import spark.*;

public class CreateGameHandler implements Route {

    DataBase database;
    HandlerMethods handlerMethods;

    public CreateGameHandler(DataBase database) {
        this.database = database;
        handlerMethods = new HandlerMethods();
    }

    @Override
    public Object handle(Request request, Response response) {
        CreateGameRequest createRequest;
        String token;
        try {
            createRequest = (CreateGameRequest) handlerMethods.getBody(request, "CreateGameRequest");
            handlerMethods.isNullString(createRequest.getGameName());
            token = handlerMethods.getAuthorization(request);
        } catch(DataAccessException ex) {
            return handlerMethods.getResponse(response,400, new CreateGameResult(null, "Error: bad request", null));
        }
        try {
            handlerMethods.isNullString(token);
            database.getAuth(token);
            createRequest.setAuthToken(token);
        } catch(DataAccessException ex) {
            return handlerMethods.getResponse(response, 401, new CreateGameResult(null, "Error: unauthorized",null));
        }
        GameService createGame = new GameService(database);
        CreateGameResult createGameResult = createGame.createGame(createRequest);
        if(createGameResult.isSuccess()) {
            return handlerMethods.getResponse(response, 200, createGameResult);
        } else {
            return handlerMethods.getResponse(response, 500, createGameResult);
        }
    }
}
