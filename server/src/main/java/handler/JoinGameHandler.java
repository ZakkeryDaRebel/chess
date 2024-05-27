package handler;

import com.google.gson.Gson;
import dataaccess.DataBase;
import request.JoinGameRequest;
import result.JoinGameResult;
import service.GameService;
import spark.Request;
import spark.Response;
import spark.Route;

public class JoinGameHandler implements Route {

    DataBase database;

    public JoinGameHandler(DataBase database) {
        this.database = database;
    }

    @Override
    public Object handle(Request request, Response response) {
        JoinGameRequest body = new Gson().fromJson(request.body(), JoinGameRequest.class);
        GameService joinGame = new GameService(database);
        JoinGameResult joinGameResult = joinGame.joinGame(body);
        if(joinGameResult.isSuccess()) {
            response.status(200);
            response.type("application/json");
            joinGameResult.nullParentVariables();
        } else {
            //Return error with the message
            response.status(500);
            response.type("application/json");
            joinGameResult.nullSuccess();
        }
        return new Gson().toJson(joinGameResult);
    }

}
