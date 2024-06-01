package dataaccess;

import chess.ChessGame;
import com.google.gson.Gson;
import model.GameData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLGameDAO implements GameDAO {

    public SQLGameDAO() {

    }

    @Override
    public void clear() throws DataAccessException {
        try (Connection conn = DatabaseManager.getConnection();) {
            var statement = conn.prepareStatement("TRUNCATE game");
            statement.executeUpdate();
        } catch(SQLException ex) {
            throw new DataAccessException("Error: " + ex.getMessage());
        }
    }

    @Override
    public void createGame(String name) throws DataAccessException {
        try(Connection conn = DatabaseManager.getConnection();) {
            var statement = conn.prepareStatement("INSERT INTO game (whiteUsername, blackUsername, gameName, json) VALUES (?,?,?,?)");
            statement.setString(1,"");
            statement.setString(2,"");
            statement.setString(3,name);
            var json = new Gson().toJson(new ChessGame());
            statement.setString(4,json);
            statement.executeUpdate();
        } catch(SQLException ex) {
            throw new DataAccessException("Error: " + ex.getMessage());
        }
    }

    @Override
    public GameData getGame(int id) throws DataAccessException {
        try (Connection conn = DatabaseManager.getConnection();){
            var statement = conn.prepareStatement("SELECT json FROM game WHERE id=?");
            statement.setInt(1, id);
            var queryResult = statement.executeQuery();
            if(queryResult.next()) {
                int gameId = queryResult.getInt("id");
                String whiteUsername = queryResult.getString("whiteUsername");
                String blackUsername = queryResult.getString("blackUsername");
                String gameName = queryResult.getString("gameName");
                ChessGame game = queryResult.getObject("game", ChessGame.class);
                return new GameData(gameId, whiteUsername, blackUsername, gameName, game);
            }
        } catch(SQLException ex) {
            throw new DataAccessException("Error: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<GameData> listGames() throws DataAccessException {
        ArrayList<GameData> gameList = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection()) {
            var ps = conn.prepareStatement("SELECT json FROM pet");
            try (var allGames = ps.executeQuery()) {
                while (allGames.next()) {
                    int gameID = allGames.getInt("id");
                    String whiteUsername = allGames.getString("whiteUsername");
                    String blackUsername = allGames.getString("blackUsername");
                    String gameName = allGames.getString("gameName");
                    ChessGame chessgame = allGames.getObject("game", ChessGame.class);
                    gameList.add(new GameData(gameID, whiteUsername, blackUsername, gameName, chessgame));
                }
                return gameList;
            }
        } catch (SQLException ex) {
            throw new DataAccessException("Error: " + ex.getMessage());
        }
    }

    @Override
    public void updateGame(int id, GameData game) throws DataAccessException {

    }

    @Override
    public int size() {
        return 0;
    }
}
