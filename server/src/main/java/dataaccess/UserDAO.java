package dataaccess;

import model.UserData;

public interface UserDAO {

        void clear();
        void createUser(String name, UserData authData);
        void removeUser(String name);
        UserData getUser(String name);

}
