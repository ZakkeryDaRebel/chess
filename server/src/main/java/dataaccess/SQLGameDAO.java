package dataaccess;

import chess.ChessGame;
import com.google.gson.Gson;
import model.GameData;
import model.UserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLGameDAO implements GameDAO {

    public SQLGameDAO() {

    }

    @Override
    public void clear() throws DataAccessException {
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("TRUNCATE game");
            statement.executeUpdate();
        } catch(SQLException ex) {
            throw new DataAccessException("Error: " + ex.getMessage());
        }
    }

    @Override
    public void createGame(String name) throws DataAccessException {
        try(Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO game (whiteUsername, blackUsername, gameName, game) VALUES (?,?,?,?)");
            statement.setString(1,"");
            statement.setString(2,"");
            statement.setString(3,name);
            String gameJson = new Gson().toJson(new ChessGame());
            statement.executeUpdate();
        } catch(SQLException ex) {
            throw new DataAccessException("Error: " + ex.getMessage());
        }
    }

    @Override
    public GameData getGame(int id) throws DataAccessException {
        try (Connection conn = DatabaseManager.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT game FROM game WHERE gameID=?");
            statement.setInt(1, id);
            ResultSet queryResult = statement.executeQuery();
            if(queryResult.next()) {
                String json = queryResult.getString("game");
                return new Gson().fromJson(json, GameData.class);
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
            PreparedStatement ps = conn.prepareStatement("SELECT json FROM game");
            try (ResultSet allGames = ps.executeQuery()) {
                while (allGames.next()) {
                    int gameID = allGames.getInt("gameID");
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
        try(Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE game SET json=? WHERE gameID=?");
            String json = new Gson().toJson(game);
            preparedStatement.setObject(1, json);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        } catch(SQLException ex) {
            throw new DataAccessException("Error: " + ex.getMessage());
        }
    }

    @Override
    public int size() {
        try(Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM game");
            ResultSet queryResult = statement.executeQuery();
            if(queryResult.next()) {
                return queryResult.getInt(1);
            }
        } catch(SQLException | DataAccessException ex) {
            return -1;
        }
        return -1;
    }
}
