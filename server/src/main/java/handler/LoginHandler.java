package handler;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.DataBase;
import model.UserData;
import request.LoginRequest;
import result.LoginResult;
import result.ParentResult;
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
            user = database.getUser(loginRequest.getUsername());
        } catch(DataAccessException ex) {
            return handlerMethods.getResponse(response, 500, new LoginResult(null, "Error: bad request", null, null));
        }
        try {
            UserService login = new UserService(database);
            LoginResult loginResult = login.loginUser(loginRequest);
            if(user.password().equals(loginRequest.getPassword())) {
                if(loginResult.isSuccess()) {
                    return handlerMethods.getResponse(response, 200, loginResult);
                } else {
                    return handlerMethods.getResponse(response, 500, loginResult);
                }
            } else
                throw new DataAccessException("Error: unauthorized");
        } catch(DataAccessException ex) {
            return handlerMethods.getResponse(response, 401, new LoginResult(null, "Error: unauthorized", null, null));
        }
    }
}
