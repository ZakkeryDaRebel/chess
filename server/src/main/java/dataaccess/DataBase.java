package dataaccess;

import model.*;

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

    //Create Methods
    public void createUser(String name, String password, String email) throws DataAccessException {
        userDataBase.createUser(name, new UserData(name, password, email));
    }
    public void createGame(String name) throws DataAccessException {
        gameDataBase.createGame(name);
    }
    public void createAuth(String token, String name) throws DataAccessException {
        authDataBase.createAuth(token, new AuthData(token, name));
    }

    //Get Methods
    public AuthData getAuth(String token) throws DataAccessException {
        return authDataBase.getAuth(token);
    }
    public UserData getUser(String name) throws DataAccessException {
        return userDataBase.getUser(name);
    }
    public GameData getGame(int gameID) throws DataAccessException {
        return gameDataBase.getGame(gameID);
    }

    //Delete Methods
    public void deleteAuth(String token) throws DataAccessException {
        authDataBase.deleteAuth(token);
    }
    public void deleteGame(int gameID) throws DataAccessException {
        gameDataBase.deleteGame(gameID);
    }
    public void deleteUser(String name) throws DataAccessException {
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
