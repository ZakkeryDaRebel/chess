package handler;

import com.google.gson.Gson;
import dataaccess.DataBase;
import request.ListGamesRequest;
import request.RegisterRequest;
import result.ListGamesResult;
import result.RegisterResult;
import service.GameService;
import service.UserService;
import spark.Request;
import spark.Response;
import spark.Route;

public class ListGamesHandler implements Route {

    DataBase database;

    public ListGamesHandler(DataBase database) {
        this.database = database;
    }

    @Override
    public Object handle(Request request, Response response) {
        ListGamesRequest body = new Gson().fromJson(request.body(), ListGamesRequest.class);
        GameService listGames = new GameService(database);
        ListGamesResult listGamesResult = listGames.listGames(body);
        if(listGamesResult.isSuccess()) {
            response.status(200);
            response.type("application/json");
            listGamesResult.nullParentVariables();
        } else {
            //Return error with the message
            response.status(500);
            response.type("application/json");
            listGamesResult.nullSuccess();
        }
        return new Gson().toJson(listGamesResult);
    }

}
