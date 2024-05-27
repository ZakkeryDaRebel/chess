package handler;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import request.*;
import result.ParentResult;
import spark.Request;
import spark.Response;

public class HandlerMethods {

    public HandlerMethods() {

    }

    public ClearAllRequest getBody(Request request, ClearAllRequest clearRequest) {
        return new Gson().fromJson(request.body(), ClearAllRequest.class);
    }

    public CreateGameRequest getBody(Request request, CreateGameRequest createRequest) {
        return new Gson().fromJson(request.body(), CreateGameRequest.class);
    }

    public JoinGameRequest getBody(Request request, JoinGameRequest joinRequest) {
        return new Gson().fromJson(request.body(), JoinGameRequest.class);
    }

    public ListGamesRequest getBody(Request request, ListGamesRequest listRequest) {
        return new Gson().fromJson(request.body(), ListGamesRequest.class);
    }

    public LoginRequest getBody(Request request, LoginRequest loginRequest) {
        return new Gson().fromJson(request.body(), LoginRequest.class);
    }

    public LogoutRequest getBody(Request request, LogoutRequest logoutRequest) {
        return new Gson().fromJson(request.body(), LogoutRequest.class);
    }

    public RegisterRequest getBody(Request request, RegisterRequest registerRequest) {
        return new Gson().fromJson(request.body(), RegisterRequest.class);
    }

    public Object getBody(Request request, String requestType) throws DataAccessException {
        if(requestType.equals("ReqisterRequest"))
            return new Gson().fromJson(request.body(), RegisterRequest.class);
        else if(requestType.equals("LogoutRequest"))
            return new Gson().fromJson(request.body(), LogoutRequest.class);

        else
            throw new DataAccessException("Invalid Request");
    }

    public String getAuthorization(Request request) {
        return request.headers("Authorization");
    }

    public String getResponse(Response response, int status, ParentResult objectClass) {
        response.status(status);
        response.type("application/json");
        objectClass.nullSuccess();
        return new Gson().toJson(objectClass);
    }
}
