package server;

import handler.ClearAllHandler;
import handler.ParentHandler;
import spark.*;

public class Server {

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        //ParentHandler handler = new ParentHandler(); SCRAP PARENT IDEA

        // Register your endpoints and handle exceptions here.
        //Spark.post("/user", )       Register
        //Spark.post("/session", )    Login
        //Spark.delete("/session", )  Logout
        //Spark.get("/game", )        ListGames
        //Spark.post("/game", )       CreateGame
        //Spark.put("/game", )        JoinGame

        //Clear
        Spark.delete("/db", new ClearAllHandler());

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }


}
