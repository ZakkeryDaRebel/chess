package dataaccess;

import model.GameData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ClearService;

public class DAOTests {

    DataBase db;

    @BeforeEach
    public void createDataBase() {
        db = new DataBase();
        ClearService clearService = new ClearService(db);
        clearService.deleteAll();
    }

    @Test
    public void clearEmptyDAOs() {
        try {
            db.clearAll();
        } catch(DataAccessException ex) {
            Assertions.fail();
        }
        Assertions.assertTrue(db.isAllEmpty());
    }

    @Test
    public void clearUsedDAOs() {
        try {
            db.createAuth("1234","test");
            createGameSuccess();
            registerNewUser();
            db.clearAll();
        } catch(Exception ex) {
            Assertions.fail();
        }
        Assertions.assertTrue(db.isAllEmpty());
    }

    @Test
    public void registerNewUser() {
        try {
            db.createUser("name", "password", "email");
            Assertions.assertNotNull(db.getUser("name"));
            Assertions.assertEquals(1, db.userDataBase.size());
        } catch(Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    public void registerExistingUsername() {
        try {
            registerNewUser();
            db.createUser("name", "1234", "email");
        } catch(Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("already taken"));
        }
    }

    @Test
    public void getNonexistingUser() {
        try {
            db.getUser("name");
        } catch(Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("Not valid Username"));
        }
    }

    @Test
    public void getNonexistingGame() {
        try {
            db.getGameName("name");
        } catch(Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("Not valid Game"));
        }
    }

    @Test
    public void getNonexistingAuth() {
        try {
            db.getAuth("1234");
        } catch(Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("Not valid"));
        }
    }

    @Test
    public void listNoGamesSuccess() {
        try {
            db.getGameList();
        } catch(Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    public void listOneGameSuccess() {
        try {
            createGameSuccess();
            db.getGameList();
        } catch(Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    public void createGameSuccess() {
        try {
            db.createGame("Test");
            Assertions.assertNotNull(db.getGame(1));
        } catch(Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    public void createGameBadRequest() {
        try {
            createGameSuccess();
            db.createGame("Test");
            Assertions.assertEquals(1, db.gameDataBase.size());
        } catch(Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("Game taken"));
        }
    }

    @Test
    public void joinGameWhiteSuccess() {
        try {
            createGameSuccess();
            GameData game = db.getGame(1);
            db.updateGame(new GameData(1, "White player", game.blackUsername(), game.gameName(), game.game()));
            GameData newGame = db.getGame(1);
            Assertions.assertNotEquals(newGame.whiteUsername(), game.whiteUsername());
        } catch(Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    public void joinGameBlackSuccess() {
        try {
            createGameSuccess();
            GameData oldGame = db.getGame(1);
            db.updateGame(new GameData(1, oldGame.whiteUsername(), "Black player", oldGame.gameName(), oldGame.game()));
            GameData newGame = db.getGame(1);
            Assertions.assertNotEquals(newGame.blackUsername(), oldGame.blackUsername());
        } catch(Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    public void createAuthSuccess() {
        try {
            db.createAuth("1234", "name");
            Assertions.assertNotNull(db.getAuth("1234"));
            Assertions.assertEquals(1, db.authDataBase.size());
        } catch(Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    public void deleteAuthSuccess() {
        try {
            createAuthSuccess();
            db.deleteAuth("1234");
            Assertions.assertEquals(0, db.authDataBase.size());
        } catch(Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    public void deleteNonexistingAuth() {
        try {
            db.deleteAuth("1234");
        } catch(Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("Not valid token"));
        }
    }
}
