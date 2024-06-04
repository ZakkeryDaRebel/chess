package dataaccess;

import request.RegisterRequest;
import result.RegisterResult;
import service.ClearService;
import service.GameService;
import service.UserService;

public class ParentTests {

    DataBase db;
    UserService userService;
    GameService gameService;
    ClearService clearService;
    RegisterResult registerResult;
    String authToken;

    public void createDataBase() {
        db = new DataBase();
        userService = new UserService(db);
        gameService = new GameService(db);
        clearService = new ClearService(db);
    }

    public void createUser() {
        registerResult = userService.createUser(new RegisterRequest("Test", "1234", "test@gmail.com"));
        authToken = registerResult.getAuthToken();
    }

}
