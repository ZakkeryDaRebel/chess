package service;

import request.*;
import result.*;
import dataaccess.DataBase;

public class ParentTests {

    DataBase db;
    UserService userService;
    GameService gameService;
    RegisterResult registerResult;
    String authToken;

    public void createDataBase() {
        db = new DataBase();
        userService = new UserService(db);
        gameService = new GameService(db);
        registerResult = userService.createUser(new RegisterRequest("Test", "1234", "test@gmail.com"));
        authToken = registerResult.getAuthToken();
    }

}
