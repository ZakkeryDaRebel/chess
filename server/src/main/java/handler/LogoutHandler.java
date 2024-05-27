package handler;

import com.google.gson.Gson;
import dataaccess.DataBase;
import request.LogoutRequest;
import result.LogoutResult;
import service.UserService;
import spark.Request;
import spark.Response;
import spark.Route;

public class LogoutHandler implements Route {

    DataBase database;

    public LogoutHandler(DataBase database) {
        this.database = database;
    }

    @Override
    public Object handle(Request request, Response response) {
        LogoutRequest body = new Gson().fromJson(request.body(), LogoutRequest.class);
        UserService logout = new UserService(database);
        LogoutResult logoutResult = logout.logoutUser(body);
        if(logoutResult.isSuccess()) {
            response.status(200);
            response.type("application/json");
            logoutResult.nullParentVariables();
        } else {
            //Return error with the message
            response.status(500);
            response.type("application/json");
            logoutResult.nullSuccess();
        }
        return new Gson().toJson(logoutResult);
    }
}
