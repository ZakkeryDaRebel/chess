package handler;

import com.google.gson.Gson;
import dataaccess.DataBase;
import request.ClearAllRequest;
import result.ClearAllResult;
import service.ClearService;
import spark.Request;
import spark.Response;
import spark.Route;

public class ClearAllHandler implements Route {

    DataBase database;

    public ClearAllHandler(DataBase database) {
        this.database = database;
    }

    @Override
    public Object handle(Request request, Response response) {
        ClearAllRequest body = new Gson().fromJson(request.body(), ClearAllRequest.class);
        ClearService clear = new ClearService(database);
        ClearAllResult clearResult = clear.deleteAll();
        if(clearResult.isSuccess()) {
            clearResult.nullParentVariables();
            response.status(200);
            response.type("application/json");
        } else {
            response.status(500);
            response.type("application/json");
            clearResult.nullSuccess();
        }
        return new Gson().toJson(clearResult);
    }
}
