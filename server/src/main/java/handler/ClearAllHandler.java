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
        //ClearService clear = new ClearService(database, body);
        ClearAllResult clearResult = clear.deleteAll();
        if(clearResult.isSuccess()) {
            //Change success to null
            //Set status to 200 because it works
            clearResult.nullParentVariables();
            response.status(200);
            response.type("application/json");
            return new Gson().toJson(clearResult);
        } else {
            //Return error with the message
            response.status(500);
            response.type("application/json");
            clearResult.nullSuccess();
            return new Gson().toJson(clearResult);
        }
    }
}
