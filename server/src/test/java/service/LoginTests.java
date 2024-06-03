package service;

import dataaccess.DataBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.*;
import result.*;

public class LoginTests {

    private UserService service;
    private RegisterResult regRes;
    private LoginResult logRes;

    @BeforeEach
    public void createDataBase() {
        DataBase db = new DataBase();
        ClearService clearService = new ClearService(db);
        clearService.deleteAll();
        service = new UserService(db);
    }

    @Test
    public void createUserCorrectLogin() {
        regRes = service.createUser( new RegisterRequest("Test", "1234", "test@gmail.com"));
        logRes = service.loginUser(new LoginRequest("Test", "1234"));
        Assertions.assertTrue(regRes.isSuccess(), regRes.getMessage());
        Assertions.assertTrue(logRes.isSuccess(), logRes.getMessage());
    }

    @Test
    public void createUserWrongPassword() {
        regRes = service.createUser( new RegisterRequest("Test", "1234", "test@gmail.com"));
        logRes = service.loginUser( new LoginRequest("Test", "5678"));
        Assertions.assertTrue(regRes.isSuccess(), regRes.getMessage());
        Assertions.assertFalse(logRes.isSuccess(), logRes.getMessage());
    }

    @Test
    public void skipCreateTryLogin() {
        logRes = service.loginUser( new LoginRequest("Test", "1234"));
        Assertions.assertFalse(logRes.isSuccess(), logRes.getMessage());
    }
}
