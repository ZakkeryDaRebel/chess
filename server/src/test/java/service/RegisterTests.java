package service;

import dataaccess.DataAccessException;
import dataaccess.DataBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegisterTests {

    DataBase db;

    @BeforeEach
    public void createDataBase() {
        db = new DataBase();
    }

    @Test
    public void RegisterNewUser() {
        try {
            db.createUser("Test", "1234", "test@gmail.com");
            System.out.println("User created! No problems");
        } catch(DataAccessException ex) {
            System.out.println("Failed Test Case, RegisterNewUser");
        }
    }

    @Test
    public void UsernameAlreadyTaken() {
        try {
            db.createUser("Test", "1234", "test@gmail.com");
            db.createUser("Test", "5678", "test2@gmail.com");
            System.out.println("Test failed, I should have run into an error.");
        } catch(DataAccessException ex) {
            System.out.println("Caught DataAccessException: " + ex.getMessage());
        }
    }
}
