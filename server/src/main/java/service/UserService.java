package service;

import dataaccess.*;
import model.*;
import request.LoginRequest;
import request.RegisterRequest;
import result.LoginResult;
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
            String authToken = newAuthToken();
            dataBase.createAuth(authToken, name);
            result = new RegisterResult(true, null, name, authToken);
        } catch(DataAccessException ex) {
            result = new RegisterResult(false, ex.getMessage(), null, null);
        }
        return result;
    }

    public LoginResult loginUser(LoginRequest request) {
        String name = request.getName();
        String password = request.getPassword();
        LoginResult result;
        try {
            UserData user = dataBase.getUser(name);
            if(user.password().equals(password)) {
                String authToken = dataBase.getAuthName(name).authToken();
                dataBase.deleteAuth(authToken);
                String newToken = newAuthToken();
                dataBase.createAuth(newToken, name);
                result = new LoginResult(true, null, name, newToken);
            }
            else
                throw new DataAccessException("Invalid Password");
        } catch(DataAccessException ex) {
            result = new LoginResult(false, ex.getMessage(), null, null);
        }
        return result;
    }

    public UserData getUser(String name) {
        try {
            return dataBase.getUser(name);
        } catch(DataAccessException ex) {
            System.out.println("Caught DataAccessException, no user to return");
            return null;
        }
    }

    public void deleteUser(String name) {
        try {
            dataBase.deleteUser(name);
        } catch(DataAccessException ex) {
            System.out.println("Caught DataAccessException, no user to delete");
        }
    }

    public String newAuthToken() {
        return UUID.randomUUID().toString();
    }
}
