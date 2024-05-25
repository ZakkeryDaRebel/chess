package service;

import dataaccess.*;
import model.*;
import request.LoginRequest;
import request.RegisterRequest;
import result.RegisterResult;

import java.util.UUID;

public class UserService {

    DataBase dataBase;

    public UserService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public RegisterResult createUser(RegisterRequest request) {
        String name = request.getName();
        String password = request.getPassword();
        String email = request.getEmail();
        RegisterResult result;
        try {
            dataBase.createUser(name, password,email);
            String authToken = UUID.randomUUID().toString();
            dataBase.createAuth(authToken, name);
            result = new RegisterResult(true, null, name, authToken);
        } catch(DataAccessException ex) {
            result = new RegisterResult(false, ex.getMessage(), null, null);
        }
        return result;
    }

    public void loginUser(LoginRequest request) {
        String name = request.getName();
        String password = request.getPassword();
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
