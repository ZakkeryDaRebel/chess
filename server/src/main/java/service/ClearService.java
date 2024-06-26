package service;

import dataaccess.*;
import result.ClearAllResult;

public class ClearService {

    DataBase database;

    public ClearService(DataBase database) {
        this.database = database;
    }

    public ClearAllResult deleteAll()  {
        ClearAllResult result;
        try {
            database.clearAll();
            if(database.isAllEmpty())
                result = new ClearAllResult(true, null);
            else
                throw new DataAccessException("Didn't clear the database");
        } catch(DataAccessException ex) {
            result = new ClearAllResult(false, "Error: " + ex.getMessage());
        }
        return result;
    }
}
