package result;

public class CreateGameResult extends ParentResult {

    private final int gameID;

    public CreateGameResult(Boolean success, String message, int gameID) {
        super(success, message);
        this.gameID = gameID;
    }

    public int getGameID() {
        return gameID;
    }
}
