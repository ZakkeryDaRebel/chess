package service;

import com.google.gson.Gson;
import dataaccess.*;
import result.ClearAllResult;

import java.util.Map;


public class ClearService {

    DataBase database;

    public ClearService(DataBase database) {
        this.database = database;
    }

    public ClearAllResult deleteAll()  {
        ClearAllResult result;
        try {
            database.clearAll();
            result = new ClearAllResult("");
        } catch(DataAccessException ex) {
            System.out.println("Caught DataAccessException Error");
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
