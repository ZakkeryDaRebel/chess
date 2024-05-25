package dataaccess;

import model.AuthData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MemoryAuthDAO implements AuthDAO {

    HashMap<String, AuthData> map;

    public MemoryAuthDAO() {
        map = new HashMap<>();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public void createAuth(String token, AuthData data) {
        map.put(token, data);
    }

    @Override
    public void deleteAuth(String token) {
        map.remove(token);
    }

    @Override
    public AuthData getAuth(String token) {
        return map.get(token);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public ArrayList<AuthData> getAllAuths() {
        ArrayList<AuthData> allAuths = new ArrayList<>();
        for(Map.Entry<String, AuthData> entry : map.entrySet()) {
            allAuths.add(entry.getValue());
        }
        return allAuths;
    }
}
