package service;

import dataaccess.DataAccessException;
import dataaccess.DataBase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTests {

    DataBase db;
    UserService service;

    @BeforeEach
    public void createDataBase() {
        db = new DataBase();
        service = new UserService(db);
    }

    @Test
    public void CreateUserCorrectLogin() {
        service.createUser("Test", "1234", "test@gmail.com");
        service.loginUser("Test", "1234");
        System.out.println("End of CreateUserCorrectLogin, expected no DataAccessException\n");
    }

    @Test
    public void CreateUserWrongPassword() {
        service.createUser("Test", "1234", "test@gmail.com");
        service.loginUser("Test", "5678");
        System.out.println("End of CreateUserWrongPassword, expected an invalid password DataAccessException\n");
    }

    @Test
    public void SkipCreateTryLogin() {
        service.loginUser("Test", "1234");
        System.out.println("End of SkipCreateTryLogin, expected a not valid username DataAccessException\n");
    }
}
