package service;

import com.google.gson.Gson;
import dataaccess.*;
import result.ClearAllResult;

import java.util.Map;


public class ClearService extends ParentService {

    AuthDAO authDB;
    GameDAO gameDB;
    UserDAO userDB;

    public ClearService() {
        authDB = getAuthDB();
        gameDB = getGameDB();
        userDB = getUserDB();
    }

    public ClearAllResult deleteAll() {
        ClearAllResult result;
        try {
            authDB.clear();
            gameDB.clear();
            userDB.clear();
            result = new ClearAllResult(null);
        } catch(DataAccessException ex) {
            //Create new ClearResponse
            //Response has message
            //return response
            var obj = Map.of("Message", "Error: Can't clear all databases");
            var serializer = new Gson();
            String json = serializer.toJson(obj);
            result = new ClearAllResult(json);
        }
        return result;
    }
}
