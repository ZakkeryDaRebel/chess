import chess.*;
import result.*;
import ui.GameBoardUI;

import java.util.Scanner;

public class Main {

    static ServerFacade serverFacade;

    public static void main(String[] args) {

        var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        System.out.println("♕ 240 Chess Client: " + piece);

        //int port =
        serverFacade = new ServerFacade(8080);

        Scanner scan = new Scanner(System.in);
        String input = "start";
        boolean loggedIn = false;

        while(!(input.equalsIgnoreCase("quit") || input.equals("2"))) {
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            if(input.equals("start")) {
                chessClientStartUp();
            } else if(input.equals("help") || input.equals("1")) {
                helpExplainOptions(loggedIn);
            } else if(!loggedIn) {
                if(input.equalsIgnoreCase("register") || input.equals("3")) {
                    registerNewUser();
                } else if(input.equalsIgnoreCase("login") || input.equals("4")) {
                    loggedIn = loginUser();
                } else {
                    invalidInput();
                }
            } else { //loggedIn = true
                if(input.equalsIgnoreCase("logout") || input.equals("3")) {
                    loggedIn = logoutUser();
                } else if(input.equalsIgnoreCase("create game") || input.equals("4")) {
                    createGame();
                } else if(input.equalsIgnoreCase("list games") || input.equals("5")) {
                    listGames();
                } else if(input.equalsIgnoreCase("play game") || input.equals("6")) {
                    joinGame();
                } else if(input.equalsIgnoreCase("observe game") || input.equals("7")) {
                    observeGame();
                } else {
                    invalidInput();
                }
            }
            listOptions(loggedIn);
            System.out.println("\nPlease input your selection: ");
            input = scan.nextLine();
        }
    }


    static void chessClientStartUp() {
        System.out.println("~ Hi! Welcome to the Chess Server! ~");
        System.out.println("Please input the number or name of what you would like to do.");
        System.out.println("If you have any questions, please enter 1 or Help for assistance and explanations.");
    }

    static void listOptions(boolean loggedIn) {
        System.out.println("\n 1. Help");
        System.out.println(" 2. Quit");
        if(!loggedIn) {
            System.out.println(" 3. Register");
            System.out.println(" 4. Login");
        } else {
            System.out.println(" 3. Logout");
            System.out.println(" 4. Create Game");
            System.out.println(" 5. List Games");
            System.out.println(" 6. Play Game");
            System.out.println(" 7. Observe Game");
        }
    }

    static void helpExplainOptions(boolean loggedIn) {
        System.out.println("\nEntering 1 or help will bring up this explanation again.");
        System.out.println("Entering 2 or quit will close down the program");
        if(!loggedIn) {
            System.out.println("Entering 3 or register will allow you to create a new user, you just need to input a username, password, and email.");
            System.out.println("Entering 4 or login will allow you to login an existing user, you just need to input your username and password.");
        } else {
            System.out.println("Entering 3 or logout will allow you to logout, no need to input anything.");
            System.out.println("Entering 4 or create game will allow you to create a new game, you just need to input a game name.");
            System.out.println("Entering 5 or list games will allow you to see all the created games.");
            System.out.println("Entering 6 or play game will allow you to join a game to play, you just need to input the gameID and team color you want to play.");
            System.out.println("Entering 7 or observe game will allow you to join a game to observe, you just need to input the gameID you want to watch.");
        }
    }

    static void registerNewUser() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nPlease enter your username: ");
        String username = scan.nextLine();
        System.out.println("Please enter your password: ");
        String password = scan.nextLine();
        System.out.println("Please enter your email: ");
        String email = scan.nextLine();
        RegisterResult result = serverFacade.registerUser(username, password, email);
        try {
            System.out.println("Successfully registered. You may now proceed to login.");
        } catch(Exception ex)  {
            System.out.println(result.getMessage());
        }
        /*System.out.println("~Implement Register~");
        System.out.println("Username: " + username + ", Password: " + password + ", Email: " + email);
         */
    }

    static void invalidInput() {
        System.out.println("\nInvalid input. If you are trying to select an option, Please input the number or the exact words of the option.");
        System.out.println("  For example: to access the help screen, please input '1' or 'help'.");
        System.out.println("If you are trying to join or observe a game, please make sure you only input the number of that game.");
        System.out.println("  For example: to join the game with ID of '1', please only enter '1', no spaces or anything but the number");
    }

    static boolean loginUser() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nPlease enter your username: ");
        String username = scan.nextLine();
        System.out.println("Please enter your password: ");
        String password = scan.nextLine();
        System.out.println("~Implement Login~");
        System.out.println("Username: " + username + ", Password: " + password);
        System.out.println("Since I haven't implemented Login yet, I am just telling the code that I am loggedIn");
        return true;
    }

    static boolean logoutUser() {
        System.out.println("\n~Implement Logout~");
        System.out.println("Since I haven't implemented Logout yet, I am just telling the code that I am not loggedIn");
        return false;
    }

    static void createGame() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nPlease enter the game name you would like to create: ");
        String gameName = scan.nextLine();
        System.out.println("~Implement Create Game~");
        System.out.println("Game Name: " + gameName);
        System.out.println("Since I haven't implemented Create Game yet, list games won't show anything");
    }

    static void listGames() {
        System.out.println("\n~Implement List Games~");
        System.out.println("Since I haven't implemented List Games yet, there is nothing to list");
    }

    static void joinGame() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nPlease enter the gameID of the game you would like to join: ");
        String gameID = scan.nextLine();
        try {
            int gameNum = Integer.parseInt(gameID);
            System.out.println("~Implement Join Game~");
            System.out.println("GameID: " + gameID);
            System.out.println("Need to make sure the input is a valid integer");
            printBoards();
            inGame();
        } catch(NumberFormatException ex) {
            invalidInput();
        }

    }

    static void observeGame() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nPlease enter the gameID of the game you would like to observe: ");
        String gameID = scan.nextLine();
        try {
            int gameNum = Integer.parseInt(gameID);
            System.out.println("~Implement Observe Game~");
            System.out.println("GameID: " + gameID);
            System.out.println("Made sure that they only pass in a valid integer for gameID, but still need to make sure there is a game with that gameID");
            printBoards();
            inGame();
        } catch(NumberFormatException ex) {
            invalidInput();
        }

    }

    static void printBoards() {
        ChessPiece[][] newBoard = new ChessGame().getBoard().getBoard();
        GameBoardUI gameBoard = new GameBoardUI(newBoard);
        System.out.println("White board: ");
        gameBoard.printWhiteSideBoard();
        System.out.println("\nBlack board: ");
        gameBoard.printBlackSideBoard();
    }

    static void inGame() {
        Scanner scan = new Scanner(System.in);
        String input = "start";
        while(!input.equalsIgnoreCase("quit")) {
            System.out.println("When you are ready to leave the game, please enter 'quit'");
            input = scan.nextLine();
        }
    }
}