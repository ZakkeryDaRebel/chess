package server;

import dataaccess.*;
import handler.ClearAllHandler;
import spark.*;

public class Server {

    DataBase database;

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        //Create DAOs to pass through
        database = new DataBase();

        // Register your endpoints and handle exceptions here.
        //Spark.post("/user", )       Register
        //Spark.post("/session", )    Login
        //Spark.delete("/session", )  Logout
        //Spark.get("/game", )        ListGames
        //Spark.post("/game", )       CreateGame
        //Spark.put("/game", )        JoinGame

        //Clear
        Spark.delete("/db", new ClearAllHandler(database));

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
