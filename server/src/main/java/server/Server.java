package server;

import com.google.gson.Gson;
import dataaccess.*;
import handler.*;
import model.AuthData;
import model.GameData;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.api.Session;
import spark.*;
import websocket.commands.*;
import websocket.messages.*;

import java.util.ArrayList;

@WebSocket
public class Server {

    DataBase database;

    public int run(int desiredPort) {
        Spark.port(desiredPort);
        Spark.staticFiles.location("web");

        Spark.webSocket("/ws", Server.class);

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

    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws Exception {
        System.out.printf("Received: %s", message);
        session.getRemote().sendString("WebSocket response: " + message);

        try {
            UserGameCommand command = new Gson().fromJson(message, UserGameCommand.class);

            // Throws a custom UnauthorizedException. Yours may work differently.
            AuthData auth = database.getAuth(command.getAuthString());
            String username = auth.username();

            //This implements a map that organizes all the sessions. GameID to an arraylist of all the authTokens
            //Put this inside of the connect method as this is the only time info is added into the map
            saveSession(command.getGameID(), session);

            switch (command.getCommandType()) {
                //Adds the authToken into the map of sessions.
                case CONNECT -> connect(session, username, (ConnectCommand) command);
                case MAKE_MOVE -> makeMove(session, username, (MakeMoveCommand) command);
                case LEAVE -> leaveGame(session, username, (LeaveGameCommand) command);
                //Doesn't remove anyone, just sends a message
                case RESIGN -> resign(session, username, (ResignCommand) command);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            sendMessage(session.getRemote(), new ErrorMessage("Error: " + ex.getMessage()));
        }
    }

    public void saveSession(Integer gameID, Session session) {
        database.addSession(gameID, session);

    }

    public void connect(Session session, String username, ConnectCommand command) {

    }

    public void makeMove(Session session, String username, MakeMoveCommand command) {

    }

    public void leaveGame(Session session, String username, LeaveGameCommand command) {
        database.removeSession(command.getGameID(), session);
        try {
            GameData game = database.getGame(command.getGameID());
            GameData newGame;
            if(game.blackUsername().equals(username))
                newGame = new GameData(game.gameID(), game.whiteUsername(), null, game.gameName(), game.game());
            else if(game.whiteUsername().equals(username))
                newGame = new GameData(game.gameID(), null, game.blackUsername(), game.gameName(), game.game());
            else
                throw new Error("Not in Game");
            database.updateGame(newGame);
        } catch(Exception ex) {
            System.out.println("NOT IN GAME!!!");
        }
        //sendMessage
        ArrayList<Session> sessionList = database.getSessionList(command.getGameID());
        for(Session sessionFromList : sessionList) {
            if(sessionFromList.equals(session))
                continue;
            sendMessage(sessionFromList.getRemote(), new NotificationMessage(username + " has left the game"));
        }
    }

    public void resign(Session session, String username, ResignCommand command) {

    }

    public void sendMessage(RemoteEndpoint endpoint, ServerMessage message) {

    }
}
