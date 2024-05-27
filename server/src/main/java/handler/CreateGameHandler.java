package handler;

import com.google.gson.Gson;
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
            token = handlerMethods.getAuthorization(request);
            createRequest.setAuthToken(token);
        } catch(DataAccessException ex) {
            return handlerMethods.getResponse(response,500, new CreateGameResult(null, "Error: Bad Request", null));
        }
        GameService createGame = new GameService(database);
        CreateGameResult createGameResult = createGame.createGame(createRequest);
        if(createGameResult.isSuccess()) {
            return handlerMethods.getResponse(response, 200, createGameResult);
        } else {
            //Return error with the message
            response.status(500);
            response.type("application/json");
            createGameResult.nullSuccess();
        }
        return new Gson().toJson(createGameResult);
    }
}
