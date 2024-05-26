package result;

import model.GameData;

import java.util.ArrayList;

public class ListGamesResult extends ParentResult {

    private final ArrayList<GameData> gameList;

    public ListGamesResult(Boolean success, String message, ArrayList<GameData> gameList) {
        super(success, message);
        this.gameList = gameList;
    }

    public ArrayList<GameData> getGameList() {
        return gameList;
    }
}
