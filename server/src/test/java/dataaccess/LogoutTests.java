package dataaccess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.LoginRequest;
import request.LogoutRequest;
import request.RegisterRequest;
import result.LoginResult;
import result.LogoutResult;
import result.RegisterResult;
import service.ClearService;
import service.UserService;

public class LogoutTests {

    private UserService service;
    private RegisterResult regRes;
    private LoginResult loginRes;
    private LogoutResult logoutRes;

    @BeforeEach
    public void createDataBase() {
        DataBase db = new DataBase();
        ClearService clearService = new ClearService(db);
        clearService.deleteAll();
        service = new UserService(db);
    }

    @Test
    public void createLoginAndLogout() {
        regRes = service.createUser( new RegisterRequest("Test", "1234", "test@gmail.com"));
        Assertions.assertTrue(regRes.isSuccess(), regRes.getMessage());
        loginRes = service.loginUser(new LoginRequest("Test", "1234"));
        Assertions.assertTrue(loginRes.isSuccess(), loginRes.getMessage());
        logoutRes = service.logoutUser(new LogoutRequest(loginRes.getAuthToken()));
        Assertions.assertTrue(logoutRes.isSuccess(), logoutRes.getMessage());
    }

    @Test
    public void noCreateTryLogout() {
        logoutRes = service.logoutUser(new LogoutRequest("987654321"));
        Assertions.assertFalse(logoutRes.isSuccess(), logoutRes.getMessage());
    }

    @Test
    public void createNoLoginTryLogout() {
        regRes = service.createUser( new RegisterRequest("Test", "1234", "test@gmail.com"));
        Assertions.assertTrue(regRes.isSuccess(), regRes.getMessage());
        logoutRes = service.logoutUser(new LogoutRequest(regRes.getAuthToken()));
        Assertions.assertTrue(logoutRes.isSuccess(), logoutRes.getMessage());
    }
}
