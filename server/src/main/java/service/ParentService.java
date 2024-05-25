package service;

import dataaccess.AuthDAO;
import dataaccess.MemoryAuthDAO;
import dataaccess.GameDAO;
import dataaccess.MemoryGameDAO;
import dataaccess.UserDAO;
import dataaccess.MemoryUserDAO;

public class ParentService {

    AuthDAO authDB;
    GameDAO gameDB;
    UserDAO userDB;

    public ParentService() {
        authDB = new MemoryAuthDAO();
        gameDB = new MemoryGameDAO();
        userDB = new MemoryUserDAO();
    }

    public AuthDAO getAuthDB() {
        return authDB;
    }

    public void setAuthDB(AuthDAO authDB) {
        this.authDB = authDB;
    }

    public GameDAO getGameDB() {
        return gameDB;
    }

    public void setGameDB(GameDAO gameDB) {
        this.gameDB = gameDB;
    }

    public UserDAO getUserDB() {
        return userDB;
    }

    public void setUserDB(UserDAO userDB) {
        this.userDB = userDB;
    }
}
