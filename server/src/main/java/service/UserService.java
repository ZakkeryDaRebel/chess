package service;

import dataaccess.*;
import model.*;

public class UserService {

    DataBase dataBase;

    public UserService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void createUser(String name, String password, String email) {
        try {
            dataBase.createUser(name, password,email);
        } catch(DataAccessException ex) {
            //Already existing User
            System.out.println("Caught DataAccessException, username already taken");
        }
    }

    public void loginUser(String name, String password) {
        try {
            dataBase.getUser(name);
        } catch(DataAccessException ex) {
            //No user
            System.out.println("Caught DataAccessException, no user by that username");
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
