package service;

import dataaccess.AuthDAO;
import dataaccess.DataBase;
import dataaccess.GameDAO;
import dataaccess.UserDAO;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Test;

public class ClearTests {

    @Test
    public void clearAll1User() {
        DataBase db = new DataBase();
        UserDAO userDB = db.getUserDataBase();
        userDB.createUser("Test", new UserData("Test", "1234", "test@gmail.com"));
        db.setUserDataBase(userDB);
        UserDAO testDB = db.getUserDataBase();
        if(testDB.getUser("Test") != null)
            System.out.println("Successfully added");
        else
            System.out.println("Failed to add");
        ClearService clear = new ClearService(db);
        clear.deleteAll();
        UserDAO clearDB = db.getUserDataBase();
        if(clearDB.getUser("Test") == null)
            System.out.println("Test Success");
        else
            System.out.println("Failure somewhere");
    }

    @Test
    public void clearAll1Game() {
        DataBase db = new DataBase();
        GameDAO gameDB = db.getGameDataBase();
        gameDB.createGame("Test");
        db.setGameDataBase(gameDB);
        GameDAO testDB = db.getGameDataBase();
        if(testDB.getGame(1) != null)
            System.out.println("Successfully added");
        else
            System.out.println("Failed to add");
        ClearService clear = new ClearService(db);
        clear.deleteAll();
        GameDAO clearDB = db.getGameDataBase();
        if(clearDB.getGame(1) == null)
            System.out.println("Test Success");
        else
            System.out.println("Failure somewhere");
    }

    @Test
    public void clearAll1Auth() {
        DataBase db = new DataBase();
        AuthDAO authDB = db.getAuthDataBase();
        authDB.createAuth("TOKEN", new AuthData("TOKEN", "Test"));
        db.setAuthDataBase(authDB);
        AuthDAO testDB = db.getAuthDataBase();
        if(testDB.getAuth("TOKEN") != null)
            System.out.println("Successfully added");
        else
            System.out.println("Failed to add");
        ClearService clear = new ClearService(db);
        clear.deleteAll();
        AuthDAO clearDB = db.getAuthDataBase();
        if(clearDB.getAuth("TOKEN") == null)
            System.out.println("Test Success");
        else
            System.out.println("Failure somewhere");
    }

    @Test
    public void clearAllEmpty() {
        DataBase db = new DataBase();
        ClearService clear = new ClearService(db);
        clear.deleteAll();
        System.out.println("End of Test");
    }
}
