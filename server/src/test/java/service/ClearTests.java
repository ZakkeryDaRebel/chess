package service;

import dataaccess.*;
import org.junit.jupiter.api.*;
import result.ClearAllResult;

import static org.junit.jupiter.api.Assertions.fail;

public class ClearTests {

    DataBase db;
    ClearService clear;
    ClearAllResult result;

    @BeforeEach
    public void createDataBase() {
        db = new DataBase();
        clear = new ClearService(db);
        ClearAllResult result;
    }

    @Test
    public void clearAll1User() {
        try {
            db.createUser("Test", "1234", "test@gmail.com");
            result = clear.deleteAll();
            Assertions.assertTrue(result.isSuccess(), result.getMessage());
        } catch(DataAccessException ex) {
            fail("Should not have caught a DataAccessException: " + ex.getMessage());
        }
    }

    @Test
    public void clearAll1Game() {
        try {
            db.createGame("Test");
            result = clear.deleteAll();
            Assertions.assertTrue(result.isSuccess(), result.getMessage());
        } catch(DataAccessException ex) {
            fail("Should not have caught a DataAccessException: " + ex.getMessage());
        }
    }

    @Test
    public void clearAll1Auth() {
        try {
            db.createAuth("TOKEN", "Test");
            result = clear.deleteAll();
            Assertions.assertTrue(result.isSuccess(), result.getMessage());
        } catch(DataAccessException ex) {
            fail("Should not have caught a DataAccessException: " + ex.getMessage());
        }
    }

    @Test
    public void clearAllEmpty() {
        ClearService clear = new ClearService(db);
        result = clear.deleteAll();
        Assertions.assertTrue(result.isSuccess(), result.getMessage());
    }
}
