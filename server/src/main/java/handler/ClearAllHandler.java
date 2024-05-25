package handler;

import service.ClearService;
import spark.Request;
import spark.Response;
import spark.Route;

public class ClearAllHandler implements Route {

    /*public ClearAllHandler() {
        ClearService clear = new ClearService();
    }*/

    @Override
    public Object handle(Request request, Response response) throws Exception {
        ClearService clear = new ClearService();
        return clear.deleteAll();
    }
}
