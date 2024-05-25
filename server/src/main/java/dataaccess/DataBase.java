package dataaccess;

import model.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class DataBase {

    AuthDAO authDataBase;
    UserDAO userDataBase;
    GameDAO gameDataBase;

    public DataBase() {
        authDataBase = new MemoryAuthDAO();
        userDataBase = new MemoryUserDAO();
        gameDataBase = new MemoryGameDAO();
    }

    public void clearAll() throws DataAccessException {
        authDataBase.clear();
        userDataBase.clear();
        gameDataBase.clear();
    }
    public boolean isAllEmpty() {
        return (authDataBase.size() == 0) && (userDataBase.size() == 0) && (gameDataBase.size() == 0);
    }

    //Create Methods
    public void createUser(String name, String password, String email) throws DataAccessException {
        if(isUserEmpty(name))
            userDataBase.createUser(name, new UserData(name, password, email));
        else
            throw new DataAccessException("Username taken");
    }
    public void createGame(String name) throws DataAccessException {
        if(isGameEmpty(name))
            gameDataBase.createGame(name);
        else
            throw new DataAccessException("Game taken");
    }
    public void createAuth(String token, String name) throws DataAccessException {
        if(isAuthEmpty(token))
            authDataBase.createAuth(token, new AuthData(token, name));
        else
            throw new DataAccessException("Auth taken");
    }

    //Get Methods
    public AuthData getAuth(String token) throws DataAccessException {
        if(isAuthEmpty(token))
            throw new DataAccessException("Not valid token");
        else
            return authDataBase.getAuth(token);
    }
    public UserData getUser(String name) throws DataAccessException {
        if(isUserEmpty(name))
            throw new DataAccessException("Not valid Username");
        else
            return userDataBase.getUser(name);
    }
    public GameData getGame(int gameID) throws DataAccessException {
        if(isGameEmpty(gameID))
            throw new DataAccessException("Not valid GameID");
        else
            return gameDataBase.getGame(gameID);
    }
    public GameData getGameName(String name) throws DataAccessException {
        ArrayList<GameData> gameList = gameDataBase.listGames();
        for(GameData game : gameList) {
            if(game.gameName().equals(name))
                return game;
        }
        throw new DataAccessException("Not valid Game Name");
    }

    //is_Empty
    public boolean isAuthEmpty(String token) throws DataAccessException {
        return authDataBase.getAuth(token) == null;
    }
    public boolean isUserEmpty(String name) throws DataAccessException {
        return userDataBase.getUser(name) == null;
    }
    public boolean isGameEmpty(int gameID) throws DataAccessException {
        return gameDataBase.getGame(gameID) == null;
    }
    public boolean isGameEmpty(String name) throws DataAccessException {
        try {
            return getGameName(name) == null;
        } catch(DataAccessException ex) {
            return true;
        }
    }

    //Delete Methods
    public void deleteAuth(String token) throws DataAccessException {
        if(isAuthEmpty(token))
            throw new DataAccessException("Not valid token");
        else
            authDataBase.deleteAuth(token);
    }
    public void deleteGame(int gameID) throws DataAccessException {
        if(isGameEmpty(gameID))
            throw new DataAccessException("Not valid GameID");
        else
            gameDataBase.deleteGame(gameID);
    }
    public void deleteUser(String name) throws DataAccessException {
        if(isUserEmpty(name))
            throw new DataAccessException("Not valid Username");
        else
            userDataBase.deleteUser(name);
    }

    //Getters and Setters for DAOs
    public AuthDAO getAuthDataBase() { return authDataBase; }
    public void setAuthDataBase(AuthDAO authDataBase) { this.authDataBase = authDataBase; }
    public UserDAO getUserDataBase() { return userDataBase; }
    public void setUserDataBase(UserDAO userDataBase) { this.userDataBase = userDataBase; }
    public GameDAO getGameDataBase() { return gameDataBase; }
    public void setGameDataBase(GameDAO gameDataBase) { this.gameDataBase = gameDataBase; }
}
