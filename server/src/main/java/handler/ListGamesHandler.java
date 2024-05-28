package handler;

import dataaccess.DataAccessException;
import dataaccess.DataBase;
import request.ListGamesRequest;
import result.JoinGameResult;
import result.ListGamesResult;
import result.LoginResult;
import service.GameService;
import spark.Request;
import spark.Response;
import spark.Route;

public class ListGamesHandler implements Route {

    DataBase database;
    HandlerMethods handlerMethods;

    public ListGamesHandler(DataBase database) {
        this.database = database;
        handlerMethods = new HandlerMethods();
    }

    @Override
    public Object handle(Request request, Response response) {
        ListGamesRequest listRequest;
        String token;
        try {
            token = handlerMethods.getAuthorization(request);
            handlerMethods.isNullString(token);
            listRequest = new ListGamesRequest(token);
        } catch(DataAccessException ex) {
            return handlerMethods.getResponse(response,400, new JoinGameResult(null, "Error: bad request"));
        }
        try {
            database.getAuth(token);
            GameService listGames = new GameService(database);
            ListGamesResult listGamesResult = listGames.listGames(listRequest);
            if(listGamesResult.isSuccess()) {
                return handlerMethods.getResponse(response, 200, listGamesResult);
            } else {
                return handlerMethods.getResponse(response, 500, listGamesResult);
            }
        } catch(DataAccessException ex) {
            return handlerMethods.getResponse(response, 401, new LoginResult(null, "Error: unauthorized", null, null));
        }
    }
}
