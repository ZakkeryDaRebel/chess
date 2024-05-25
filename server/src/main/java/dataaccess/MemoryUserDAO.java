package dataaccess;

import model.UserData;

import java.util.HashMap;

public class MemoryUserDAO implements UserDAO {

    HashMap<String, UserData> map;

    public MemoryUserDAO() {
        map = new HashMap<>();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public void createUser(String name, UserData authData) {
        map.put(name, authData);
    }

    @Override
    public void deleteUser(String name) {
        map.remove(name);
    }

    @Override
    public UserData getUser(String name) {
        return map.get(name);
    }
}
