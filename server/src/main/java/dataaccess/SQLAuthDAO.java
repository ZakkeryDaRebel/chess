package dataaccess;

import model.AuthData;

public class SQLAuthDAO implements AuthDAO {

    public SQLAuthDAO() {

    }

    @Override
    public void clear() throws DataAccessException {

    }

    @Override
    public void createAuth(String token, AuthData data) throws DataAccessException {

    }

    @Override
    public void deleteAuth(String token) throws DataAccessException {

    }

    @Override
    public AuthData getAuth(String token) throws DataAccessException {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
