package service;

import dataaccess.*;
import model.*;
import result.RegisterResult;

public class UserService {

    DataBase dataBase;

    public UserService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public RegisterResult createUser(String name, String password, String email) {
        try {
            dataBase.createUser(name, password,email);
            //Create Auth Token
            //Add token to database along with Username
            //Return RegisterResult of Username and Authtoken
        } catch(DataAccessException ex) {
            //Already existing User
            System.out.println("Caught DataAccessException, username already taken");
        }
        return null;
    }

    public void loginUser(String name, String password) {
        try {
            UserData user = dataBase.getUser(name);
            if(user.password().equals(password)) {
                System.out.println("Login valid");
            }
            else
                throw new DataAccessException("Invalid Password");
        } catch(DataAccessException ex) {
            //No user
            System.out.println("Caught DataAccessException: " + ex.getMessage());

        }
    }

    public UserData getUser(String name) {
        try {
            return dataBase.getUser(name);
        } catch(DataAccessException ex) {
            //No User to return
            System.out.println("Caught DataAccessException, no user to return");
            return null;
        }
    }

    public void deleteUser(String name) {
        try {
            dataBase.deleteUser(name);
        } catch(DataAccessException ex) {
            //No User to delete
            System.out.println("Caught DataAccessException, no user to delete");
        }
    }
}
