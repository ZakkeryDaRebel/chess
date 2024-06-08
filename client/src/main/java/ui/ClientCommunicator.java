package ui;
import com.google.gson.Gson;
import request.*;
import result.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class ClientCommunicator {

    int port;

    public ClientCommunicator(int port) {
        this.port = port;
    }

    public InputStreamReader registerUser(RegisterRequest request) {
        try {
            //parameter path
            String urlString = "http://localhost:" + port + "/user";
            URI uri = new URI(urlString);
            HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
            //parameter
            http.setRequestMethod("POST");
            http.setDoOutput(true);

            try(OutputStream requestBody = http.getOutputStream();) {
                // Write request body to OutputStream ...
                String json = new Gson().toJson(request);
                requestBody.write(json.getBytes());
            }

            // Make the request
            http.connect();
            InputStreamReader reader;

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // Get HTTP response headers, if necessary
                // Map<String, List<String>> headers = connection.getHeaderFields();

                // OR

                //connection.getHeaderField("Content-Length");

                InputStream responseBody = http.getInputStream();
                // Read response body from InputStream ...
                reader = new InputStreamReader(responseBody);
                //return Gson.fromJson(reader, class)
            }
            else {
                // SERVER RETURNED AN HTTP ERROR

                InputStream responseBody = http.getErrorStream();
                // Read and process error response body from InputStream ...
                reader = new InputStreamReader(responseBody);
            }
            return reader;


        } catch(URISyntaxException uriException) {
            System.out.println("There is something wrong with my URL");
        } catch(java.net.ProtocolException protocalException) {
            System.out.println("I can't set my request to GET");
        } catch(IOException ioException) {
            System.out.println("Something went wrong while trying to connect");
        }
        return null;
    }

    public LoginResult loginUser(LoginRequest request) {
        return null;
    }

    public LogoutResult logoutUser(LogoutRequest request) {
        return null;
    }

    public CreateGameResult createGame(CreateGameRequest request) {
        return null;
    }

    public ListGamesResult listGames(ListGamesRequest request) {
        return null;
    }

    public JoinGameRequest joinGame(JoinGameRequest request) {
        return null;
    }

    public ClearAllResult clear(ClearAllRequest request) {
        return null;
    }


}
