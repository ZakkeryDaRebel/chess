package request;

public class CreateGameRequest extends ParentRequest {

    private final String authToken;
    private final String gameName;

    public CreateGameRequest(String authToken, String gameName) {
        super();
        this.authToken = authToken;
        this.gameName = gameName;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getGameName() {
        return gameName;
    }
}
