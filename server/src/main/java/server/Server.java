package server;

import dataaccess.*;
import handler.*;
import spark.*;
public class Server {

    DataBase database;

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

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
