package handler;

import dataaccess.DataAccessException;
import dataaccess.DataBase;
import model.UserData;
import request.LoginRequest;
import result.LoginResult;
import service.UserService;
import spark.Request;
import spark.Response;
import spark.Route;

public class LoginHandler implements Route {

    DataBase database;
    HandlerMethods handlerMethods;

    public LoginHandler(DataBase database) {
        this.database = database;
        handlerMethods = new HandlerMethods();
    }

    @Override
    public Object handle(Request request, Response response) {
        LoginRequest loginRequest;
        UserData user;
        try {
            loginRequest = (LoginRequest) handlerMethods.getBody(request, "LoginRequest");
            handlerMethods.isNullString(loginRequest.getUsername());
            handlerMethods.isNullString(loginRequest.getPassword());
        } catch(DataAccessException ex) {
            return handlerMethods.getResponse(response, 401, new LoginResult(null, "Error: bad request", null, null));
        }
        try {
            user = database.getUser(loginRequest.getUsername());
            UserService login = new UserService(database);
            LoginResult loginResult = login.loginUser(loginRequest);
            if(loginResult.isSuccess()) {
                return handlerMethods.getResponse(response, 200, loginResult);
            } else if(loginResult.getMessage().contains("Unauthorized")) {
                return handlerMethods.getResponse(response, 401, new LoginResult(null, "Error: unauthorized", null, null));
            } else {
                return handlerMethods.getResponse(response, 500, loginResult);
            }
        } catch(DataAccessException ex) {
            return handlerMethods.getResponse(response, 401, new LoginResult(null, "Error: unauthorized", null, null));
        }
    }
}
