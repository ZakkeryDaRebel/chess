package dataaccess;

import model.UserData;

public interface UserDAO {

        void clear() throws DataAccessException;
        void createUser(String name, UserData authData) throws DataAccessException;
        void deleteUser(String name) throws DataAccessException;
        UserData getUser(String name) throws DataAccessException;
        int size();

}
