package handler;

import com.google.gson.Gson;
import dataaccess.DataBase;
import request.RegisterRequest;
import result.RegisterResult;
import service.UserService;
import spark.Request;
import spark.Response;
import spark.Route;

public class RegisterHandler implements Route {

    DataBase database;

    public RegisterHandler(DataBase database) {
        this.database = database;
    }

    @Override
    public Object handle(Request request, Response response) {
        RegisterRequest body = new Gson().fromJson(request.body(), RegisterRequest.class);
        UserService register = new UserService(database);
        RegisterResult registerResult = register.createUser(body);
        if(registerResult.isSuccess()) {
            response.status(200);
            response.type("application/json");
            registerResult.nullParentVariables();
        } else {
            //Return error with the message
            response.status(500);
            response.type("application/json");
            registerResult.nullSuccess();
        }
        return new Gson().toJson(registerResult);
    }
}
