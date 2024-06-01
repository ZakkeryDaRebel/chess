package server;

import dataaccess.*;
import handler.*;
import spark.*;

import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class Server {

    DataBase database;

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");


        //This is my test with pet_store SQL

        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "P=Wg<ch#74or;#.kK^jC")) {
            try (var preparedStatement = conn.prepareStatement("SELECT 1+1")) {
                var rs = preparedStatement.executeQuery();
                rs.next();
                System.out.println(rs.getInt(1));
            }

            var dropDbStatement = conn.prepareStatement("DROP DATABASE IF EXISTS pet_store");
            dropDbStatement.executeUpdate();

            var createDbStatement = conn.prepareStatement("CREATE DATABASE IF NOT EXISTS pet_store");
            createDbStatement.executeUpdate();

            conn.setCatalog("pet_store");

            var createPetTable = """
            CREATE TABLE  IF NOT EXISTS pet (
                id INT NOT NULL AUTO_INCREMENT,
                name VARCHAR(255) NOT NULL,
                type VARCHAR(255) NOT NULL,
                PRIMARY KEY (id)
            )""";
            try (var createTableStatement = conn.prepareStatement(createPetTable)) {
                createTableStatement.executeUpdate();
            }


            String name = "Fido";
            String type = "dog";
            try (var preparedStatement = conn.prepareStatement("INSERT INTO pet (name, type) VALUES(?, ?)", RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, type);
                preparedStatement.executeUpdate();
                var resultSet = preparedStatement.getGeneratedKeys();
                var ID = 0;
                if (resultSet.next()) {
                    ID = resultSet.getInt(1);
                }
                System.out.println(ID);
            }
            var newPet = conn.prepareStatement("INSERT INTO pet (name, type) VALUES ('Chilli', 'Heeler Dog')");
            newPet.executeUpdate();

        } catch(SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        //End of SQL Test


        //Create DAOs to pass through
        database = new DataBase();

        // Register your endpoints and handle exceptions here.
        Spark.post("/user", (req, res) -> (new RegisterHandler(database)).handle(req,res));
        Spark.post("/session", (req, res) -> (new LoginHandler(database)).handle(req,res));
        Spark.delete("/session", (req, res) -> (new LogoutHandler(database)).handle(req, res));
        Spark.get("/game", (req, res) -> (new ListGamesHandler(database)).handle(req, res));
        Spark.post("/game", (req, res) -> (new CreateGameHandler(database)).handle(req, res));
        Spark.put("/game", (req, res) -> (new JoinGameHandler(database)).handle(req, res));
        Spark.delete("/db", (req, res) -> (new ClearAllHandler(database)).handle(req, res));

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
