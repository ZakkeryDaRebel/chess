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
        }
    }

    public UserData getUser(String name) {
        try {
            return dataBase.getUser(name);
        } catch(DataAccessException ex) {
            //No User to return
            return null;
        }
    }

    public void deleteUser(String name) {
        try {
            dataBase.deleteUser(name);
        } catch(DataAccessException ex) {
            //No User to delete
        }
    }
}
