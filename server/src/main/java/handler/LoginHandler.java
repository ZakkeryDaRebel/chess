package handler;

import com.google.gson.Gson;
import dataaccess.DataBase;
import request.LoginRequest;
import result.LoginResult;
import service.UserService;
import spark.Request;
import spark.Response;
import spark.Route;

public class LoginHandler implements Route {

    DataBase database;

    public LoginHandler(DataBase database) {
        this.database = database;
    }

    @Override
    public Object handle(Request request, Response response) {
        LoginRequest body = new Gson().fromJson(request.body(), LoginRequest.class);
        UserService login = new UserService(database);
        LoginResult loginResult = login.loginUser(body);
        if(loginResult.isSuccess()) {
            response.status(200);
            response.type("application/json");
            loginResult.nullParentVariables();
        } else {
            //Return error with the message
            response.status(500);
            response.type("application/json");
            loginResult.nullSuccess();
        }
        return new Gson().toJson(loginResult);
    }

}
