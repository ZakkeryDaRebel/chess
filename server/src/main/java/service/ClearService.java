package service;

import dataaccess.AuthDAO;
import dataaccess.MemoryAuthDAO;

public class ClearService {



    public ClearService() {
        AuthDAO auth = new MemoryAuthDAO();
        deleteAllAuths();
        deleteAllUsers();
        deleteAllGames();
    }

    public void deleteAllAuths() {

    }

    public void deleteAllUsers() {

    }

    public void deleteAllGames() {

    }
}
