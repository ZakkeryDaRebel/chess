package service;

import dataaccess.*;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Test;

public class ClearTests {

    @Test
    public void clearAll1User() {
        try {
            DataBase db = new DataBase();
            db.createUser("Test", "1234", "test@gmail.com");
            UserDAO testDB = db.getUserDataBase();
            if (testDB.getUser("Test") != null)
                System.out.println("Successfully added");
            else
                System.out.println("Failed to add");
            ClearService clear = new ClearService(db);
            clear.deleteAll();
            UserDAO clearDB = db.getUserDataBase();
            if (clearDB.getUser("Test") == null)
                System.out.println("Test Success");
            else
                System.out.println("Failure somewhere");
        } catch(DataAccessException ex) {
            System.out.println("Caught DataAccessException");
        }
    }

    @Test
    public void clearAll1Game() {
        try {
            DataBase db = new DataBase();
            db.createGame("Test");
            GameDAO testDB = db.getGameDataBase();
            if (testDB.getGame(1) != null)
                System.out.println("Successfully added");
            else
                System.out.println("Failed to add");
            ClearService clear = new ClearService(db);
            clear.deleteAll();
            GameDAO clearDB = db.getGameDataBase();
            if (clearDB.getGame(1) == null)
                System.out.println("Test Success");
            else
                System.out.println("Failure somewhere");
        } catch(DataAccessException ex) {
            System.out.println("Caught DataAccessException");
        }
    }

    @Test
    public void clearAll1Auth() {
        try {
            DataBase db = new DataBase();
            db.createAuth("TOKEN", "Test");
            AuthDAO testDB = db.getAuthDataBase();
            if (testDB.getAuth("TOKEN") != null)
                System.out.println("Successfully added");
            else
                System.out.println("Failed to add");
            ClearService clear = new ClearService(db);
            clear.deleteAll();
            AuthDAO clearDB = db.getAuthDataBase();
            if (clearDB.getAuth("TOKEN") == null)
                System.out.println("Test Success");
            else
                System.out.println("Failure somewhere");
        } catch(DataAccessException ex) {
            System.out.println("Caught DataAccessException");
        }
    }

    @Test
    public void clearAllEmpty() {
        DataBase db = new DataBase();
        ClearService clear = new ClearService(db);
        clear.deleteAll();
        System.out.println("End of Test");
    }
}
