package handler;

import dataaccess.DataBase;
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
        ClearService clear = new ClearService(database);
        return clear.deleteAll();
    }
}
