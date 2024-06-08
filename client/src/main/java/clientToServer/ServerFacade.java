package clientToServer;

import com.google.gson.Gson;
import request.*;
import result.*;

import java.io.InputStreamReader;

public class ServerFacade {

    String authToken;
    ClientCommunicator communicator;

    public ServerFacade(int port) {
        authToken = null;
        communicator = new ClientCommunicator(port);
    }

    public RegisterResult registerUser(String username, String password, String email) {
        RegisterRequest request = new RegisterRequest(username, password, email);
        URLClientStrings clientStrings = new URLClientStrings("/user", "POST", "");
        InputStreamReader reader = communicator.clientToServer(request, clientStrings);
        return new Gson().fromJson(reader, RegisterResult.class);
    }

    public LoginResult loginUser(String username, String password) {
        LoginRequest request = new LoginRequest(username, password);
        URLClientStrings clientString = new URLClientStrings("/session", "POST", "");
        InputStreamReader reader = communicator.clientToServer(request, clientString);
        return new Gson().fromJson(reader, LoginResult.class);
    }

    public LogoutResult logoutUser() {
        LogoutRequest request = new LogoutRequest(authToken);
        URLClientStrings clientStrings = new URLClientStrings("/session", "DELETE", authToken);
        InputStreamReader reader = communicator.clientToServer(request, clientStrings);
        return new Gson().fromJson(reader, LogoutResult.class);
    }

    public CreateGameResult createGame(String gameName) {
        CreateGameRequest request = new CreateGameRequest(gameName, authToken);
        return null;
    }

    public ListGamesResult listGames() {
        ListGamesRequest request = new ListGamesRequest(authToken);
        return null;
    }

    public void joinGame(Integer gameID, String playerColor) {
        JoinGameRequest request = new JoinGameRequest(authToken, playerColor, gameID);
    }

    public void clear() {
        ClearAllRequest request = new ClearAllRequest();
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
