package service;

import dataaccess.DataAccessException;
import dataaccess.DataBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.RegisterResult;

import static org.junit.jupiter.api.Assertions.fail;

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
        result = service.createUser("Test", "1234", "test@gmail.com");


        try {
            db.createUser("Test", "1234", "test@gmail.com");
        } catch(DataAccessException ex) {
            fail("Should not have caught a DataAccessException: " + ex.getMessage());
        }
    }

    @Test
    public void UsernameAlreadyTaken() {
        try {
            db.createUser("Test", "1234", "test@gmail.com");
            db.createUser("Test", "5678", "test2@gmail.com");
            fail("Should There should have been a problem with creating two Users with the same Username");
        } catch(DataAccessException ex) {
            Assertions.assertEquals(ex.getMessage(), "Username taken", "Caught a different DataAccessException than expected" + ex.getMessage());
        }
    }
}
