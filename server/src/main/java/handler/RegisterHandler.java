package handler;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.DataBase;
import request.RegisterRequest;
import result.CreateGameResult;
import result.RegisterResult;
import service.UserService;
import spark.Request;
import spark.Response;
import spark.Route;

public class RegisterHandler implements Route {

    DataBase database;
    HandlerMethods handlerMethods;

    public RegisterHandler(DataBase database) {
        this.database = database;
        handlerMethods = new HandlerMethods();
    }

    @Override
    public Object handle(Request request, Response response) {
        RegisterRequest registerRequest;
        try {
            registerRequest = (RegisterRequest) handlerMethods.getBody(request, "RegisterRequest");
            handlerMethods.hasUsername(registerRequest.getUsername());
            handlerMethods.hasPassword(registerRequest.getPassword());
            handlerMethods.hasEmail(registerRequest.getEmail());
        } catch(DataAccessException ex) {
            return handlerMethods.getResponse(response,400, new RegisterResult(null, "Error: Bad Request", null, null));
        }
        UserService register = new UserService(database);
        RegisterResult registerResult = register.createUser(registerRequest);
        if(registerResult.isSuccess()) {
            return handlerMethods.getResponse(response, 200, registerResult);
        } else if(registerResult.getMessage().contains("already taken")) {
            return handlerMethods.getResponse(response, 403, registerResult);
        } else
            return handlerMethods.getResponse(response, 500, registerResult);
    }
}
