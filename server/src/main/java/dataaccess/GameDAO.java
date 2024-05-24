package dataaccess;

import model.GameData;

import java.util.ArrayList;

public interface GameDAO {
    void clear();
    void createGame(String name);
    GameData getGame(int id);
    ArrayList<GameData> listGames();
    void updateGame(int id, GameData game);
    void deleteGame(int id);
}
