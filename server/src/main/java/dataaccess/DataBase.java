package dataaccess;

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

    public AuthDAO getAuthDataBase() {
        return authDataBase;
    }

    public void setAuthDataBase(AuthDAO authDataBase) {
        this.authDataBase = authDataBase;
    }

    public UserDAO getUserDataBase() {
        return userDataBase;
    }

    public void setUserDataBase(UserDAO userDataBase) {
        this.userDataBase = userDataBase;
    }

    public GameDAO getGameDataBase() {
        return gameDataBase;
    }

    public void setGameDataBase(GameDAO gameDataBase) {
        this.gameDataBase = gameDataBase;
    }
}
