import com.google.gson.Gson;
import request.*;
import result.*;
import ui.ClientCommunicator;

import java.io.InputStreamReader;

public class ServerFacade {

    String authToken;
    ClientCommunicator communicator;

    public ServerFacade(int port) {
        authToken = "";
        communicator = new ClientCommunicator(port);
    }

    public RegisterResult registerUser(String username, String password, String email) {
        RegisterRequest request = new RegisterRequest(username, password, email);
        InputStreamReader reader = communicator.registerUser(request);

        return new Gson().fromJson(reader, RegisterResult.class);
    }

    public LoginResult loginUser(String username, String password) {
        LoginRequest request = new LoginRequest(username, password);
        LoginResult result = communicator.loginUser(request);
        if(result.isSuccess())
            authToken = result.getAuthToken();
        return result;
    }

    public LogoutResult logoutUser() {
        LogoutRequest request = new LogoutRequest(authToken);
        LogoutResult result = communicator.logoutUser(request);
        if(result.isSuccess())
            authToken = null;
        return result;
    }

    public CreateGameResult createGame(String gameName) {
        CreateGameRequest request = new CreateGameRequest(gameName, authToken);
        return communicator.createGame(request);
    }

    public ListGamesResult listGames() {
        ListGamesRequest request = new ListGamesRequest(authToken);
        return communicator.listGames(request);
    }

    public void joinGame(Integer gameID, String playerColor) {
        JoinGameRequest request = new JoinGameRequest(authToken, playerColor, gameID);
    }

    public void clear() {

    }
}
