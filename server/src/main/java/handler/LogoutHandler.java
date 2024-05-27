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
        String header = request.headers("Authorization");
        LogoutRequest body = new LogoutRequest(header);
        UserService logout = new UserService(database);
        LogoutResult logoutResult = logout.logoutUser(body);
        if(logoutResult.isSuccess()) {
            response.type("application/json");
            response.status(200);
            logoutResult.nullParentVariables();
        } else {
            //Return error with the message
            response.type("application/json");
            response.status(500);
            logoutResult.nullSuccess();
        }
        return new Gson().toJson(logoutResult);
    }
}
