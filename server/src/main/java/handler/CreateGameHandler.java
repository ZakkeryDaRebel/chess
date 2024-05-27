package handler;

import com.google.gson.Gson;
import dataaccess.DataBase;
import request.CreateGameRequest;
import request.RegisterRequest;
import result.CreateGameResult;
import result.RegisterResult;
import service.GameService;
import service.UserService;
import spark.Request;
import spark.Response;
import spark.Route;

public class CreateGameHandler implements Route {

    DataBase database;

    public CreateGameHandler(DataBase database) {
        this.database = database;
    }

    @Override
    public Object handle(Request request, Response response) {
        CreateGameRequest body = new Gson().fromJson(request.body(), CreateGameRequest.class);
        GameService createGame = new GameService(database);
        CreateGameResult createGameResult = createGame.createGame(body);
        if(createGameResult.isSuccess()) {
            response.status(200);
            response.type("application/json");
            createGameResult.nullParentVariables();
        } else {
            //Return error with the message
            response.status(500);
            response.type("application/json");
            createGameResult.nullSuccess();
        }
        return new Gson().toJson(createGameResult);
    }
}
