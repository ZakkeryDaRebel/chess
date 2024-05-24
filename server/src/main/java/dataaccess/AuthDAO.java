package dataaccess;

import model.AuthData;

public interface AuthDAO {
    void clear();
    void createAuth(String token, AuthData data);
    void deleteAuth(String token);
    AuthData getAuth(String token);
}
