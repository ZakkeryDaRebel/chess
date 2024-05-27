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
    HandlerMethods handlerMethods;

    public ClearAllHandler(DataBase database) {
        this.database = database;
        handlerMethods = new HandlerMethods();
    }

    @Override
    public Object handle(Request request, Response response) {
        ClearService clear = new ClearService(database);
        ClearAllResult clearResult = clear.deleteAll();
        if(clearResult.isSuccess()) {
            handlerMethods.getResponse(response, 200, clearResult);
        } else {
            handlerMethods.getResponse(response, 500, clearResult);
        }
        return new Gson().toJson(clearResult);
    }
}
