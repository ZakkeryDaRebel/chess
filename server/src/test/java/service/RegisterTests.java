package service;

import dataaccess.DataAccessException;
import dataaccess.DataBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import result.RegisterResult;

public class RegisterTests {

    DataBase db;
    UserService service;
    RegisterResult result;

    @BeforeEach
    public void createDataBase() {
        db = new DataBase();
        service = new UserService(db);
    }

    @Test
    public void RegisterNewUser() {
        result = service.createUser( new RegisterRequest("Test", "1234", "test@gmail.com"));
        Assertions.assertTrue(result.isSuccess(), result.getMessage());
    }

    @Test
    public void UsernameAlreadyTaken() {
        result = service.createUser( new RegisterRequest("Test", "1234", "test@gmail.com"));
        Assertions.assertTrue(result.isSuccess(), result.getMessage());
        result = service.createUser( new RegisterRequest("Test", "5678", "test2@gmail.com"));
        Assertions.assertFalse(result.isSuccess(), result.getMessage());
    }

    @Test
    public void CreateTwoUsers() {
        result = service.createUser(new RegisterRequest("Test", "1234", "test@gmail.com"));
        Assertions.assertTrue(result.isSuccess(), result.getMessage());
        result = service.createUser(new RegisterRequest("Test2", "8745", "Test2@gmail.com"));
        Assertions.assertTrue(result.isSuccess(), result.getMessage());
    }
}
