package handler;

import com.google.gson.Gson;
import dataaccess.DataBase;
import request.ListGamesRequest;
import result.ListGamesResult;
import service.GameService;
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
        String header = request.headers("Authorization");
        ListGamesRequest body = new ListGamesRequest(header);
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
