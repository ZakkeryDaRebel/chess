package client;

import clienttoserver.ServerFacade;
import org.junit.jupiter.api.*;
import result.*;
import server.Server;


public class ServerFacadeTests {

    private static Server server;
    private static ServerFacade facade;
    private static String authToken;

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
        facade = new ServerFacade(port);
    }

    @BeforeEach
    void clearServer() {facade.clear();}

    @AfterAll
    static void stopServer() {
        server.stop();
    }


    @Test
    public void sampleTest() {
        Assertions.assertTrue(true);
    }

    @Test
    public void registerNewUser() {
        RegisterResult result = facade.registerUser("username", "password", "email");
        Assertions.assertNull(result.getMessage());
        authToken = result.getAuthToken();
    }

    @Test
    public void registerExistingUser() {
        registerNewUser();
        RegisterResult result = facade.registerUser("username", "1234", "email");
        Assertions.assertNotNull(result.getMessage());
    }

    @Test
    public void registerMultipleUsers() {
        registerNewUser();
        RegisterResult result1 = facade.registerUser("test", "1234", "test@gmail.com");
        Assertions.assertNull(result1.getMessage());
        RegisterResult result2 = facade.registerUser("bob", "secret", "bob@gmail.com");
        Assertions.assertNull(result2.getMessage());
    }

    @Test
    public void registerIncomplete() {
        RegisterResult result = facade.registerUser(null, "1234", "email");
        Assertions.assertNotNull(result.getMessage());
    }

    @Test
    public void loginSuccessful() {
        logoutSuccessful();
        LoginResult result = facade.loginUser("username", "password");
        Assertions.assertNull(result.getMessage());
        authToken = result.getAuthToken();
    }

    @Test
    public void loginNonexistingUser() {
        LoginResult result = facade.loginUser("username", "password");
        Assertions.assertNotNull(result.getMessage());
    }

    @Test
    public void loginWrongPassword() {
        logoutSuccessful();
        LoginResult result = facade.loginUser("username", "1234");
        Assertions.assertNotNull(result.getMessage());
    }

    @Test
    public void logoutSuccessful() {
        registerNewUser();
        LogoutResult result = facade.logoutUser(authToken);
        Assertions.assertNull(result.getMessage());
    }

    @Test
    public void logoutWithoutLogginIn() {
        registerNewUser();
        LogoutResult result = facade.logoutUser("abcdefghijklmnopqrstuvwxyz");
        Assertions.assertNotNull(result.getMessage());
    }

    @Test
    public void createGame() {
        registerNewUser();
        CreateGameResult result = facade.createGame("New Game", authToken);
        Assertions.assertNull(result.getMessage());
    }

    @Test
    public void createExistingGame() {
        createGame();
        CreateGameResult result = facade.createGame("New Game", authToken);
        Assertions.assertNotNull(result.getMessage());
    }

    @Test
    public void createGameWithoutLoggingIn() {
        logoutSuccessful();
        CreateGameResult result = facade.createGame("New Game", "abcdefghijklmnopqrstuvwxyz");
        Assertions.assertNotNull(result.getMessage());
    }

    @Test
    public void listNoGames() {
        loginSuccessful();
        ListGamesResult result = facade.listGames(authToken);
        Assertions.assertNull(result.getMessage());
    }

    @Test
    public void listOneGame() {
        createGame();
        ListGamesResult result = facade.listGames(authToken);
        Assertions.assertNull(result.getMessage());
    }

    @Test
    public void listMultipleGames() {
        createGame();
        CreateGameResult result1 = facade.createGame("PRO GAME!!!", authToken);
        Assertions.assertNull(result1.getMessage());
        CreateGameResult result2 = facade.createGame("Beginner game", authToken);
        Assertions.assertNull(result2.getMessage());
        ListGamesResult result3 = facade.listGames(authToken);
        Assertions.assertNull(result3.getMessage());
    }

    @Test
    public void joinEmptyWhite() {
        createGame();
        JoinGameResult result = facade.joinGame(1, "WHITE", authToken);
        Assertions.assertNull(result.getMessage());
    }

    @Test
    public void joinEmptyBlack() {
        createGame();
        JoinGameResult result = facade.joinGame(1, "BLACK", authToken);
        Assertions.assertNull(result.getMessage());
    }

    @Test
    public void joinFullWhite() {
        joinEmptyWhite();
        JoinGameResult result = facade.joinGame(1, "WHITE", authToken);
        Assertions.assertNotNull(result.getMessage());
    }

    @Test
    public void joinFullBlack() {
        joinEmptyBlack();
        JoinGameResult result = facade.joinGame(1, "BLACK", authToken);
        Assertions.assertNotNull(result.getMessage());
    }

    @Test
    public void joinSpectator() {
        joinEmptyWhite();
        JoinGameResult result = facade.joinGame(1, "SPECTATOR", authToken);
        Assertions.assertNull(result.getMessage());
    }
}
