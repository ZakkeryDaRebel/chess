package dataaccess;

import model.AuthData;

public interface AuthDAO {
    void clear() throws DataAccessException;
    void createAuth(String token, AuthData data);
    void deleteAuth(String token);
    AuthData getAuth(String token);
}
