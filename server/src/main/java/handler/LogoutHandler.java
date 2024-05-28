package handler;

import dataaccess.DataAccessException;
import dataaccess.DataBase;
import model.AuthData;
import request.LogoutRequest;
import result.LoginResult;
import result.LogoutResult;
import service.UserService;
import spark.Request;
import spark.Response;
import spark.Route;

public class LogoutHandler implements Route {

    DataBase database;
    HandlerMethods handlerMethods;

    public LogoutHandler(DataBase database) {
        this.database = database;
        handlerMethods = new HandlerMethods();
    }

    @Override
    public Object handle(Request request, Response response) {
        LogoutRequest logoutRequest;
        String token;
        try {
            token = handlerMethods.getAuthorization(request);
            handlerMethods.isNullString(token);
            logoutRequest = new LogoutRequest(token);
        } catch(DataAccessException ex) {
            return handlerMethods.getResponse(response,400, new LogoutResult(null, "Error: bad request"));
        }
        try {
            AuthData auth = database.getAuth(token);
            UserService logout = new UserService(database);
            LogoutResult logoutResult = logout.logoutUser(logoutRequest);
            if(logoutResult.isSuccess())
                return handlerMethods.getResponse(response, 200, logoutResult);
            else
                return handlerMethods.getResponse(response, 500, logoutResult);
        } catch(DataAccessException ex) {
            return handlerMethods.getResponse(response, 401, new LoginResult(null, "Error: unauthorized", null, null));
        }
    }
}
