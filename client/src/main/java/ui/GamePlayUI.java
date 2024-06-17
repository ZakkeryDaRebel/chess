package ui;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import com.google.gson.Gson;
import model.GameData;
import websocket.commands.*;
import websocket.messages.*;
import java.util.Scanner;
import javax.websocket.*;
import java.net.URI;

public class GamePlayUI extends Endpoint {

    private Integer gameID;
    private String teamColor;
    public Session session;
    private String authToken;
    private GameData gameData;
    private boolean loadedGame;

    public GamePlayUI(Integer gameNum, String teamColor, String token) {
        gameID = gameNum;
        this.teamColor = teamColor;
        authToken = token;
        loadedGame = false;
    }

    public void send(String msg) throws Exception {
        this.session.getBasicRemote().sendText(msg);
    }

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
    }



    public boolean tryConnectToGame() {
        try {
            URI uri = new URI("ws://localhost:8080/ws");
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            this.session = container.connectToServer(this, uri);
            String json = new Gson().toJson(new ConnectCommand(authToken, gameID));

            this.session.addMessageHandler(new MessageHandler.Whole<String>() {
                @Override
                public void onMessage(String message) {
                    ServerMessage serverMessage = new Gson().fromJson(message, ServerMessage.class);
                    switch(serverMessage.getServerMessageType()) {
                        case NOTIFICATION -> {
                            NotificationMessage notification = new Gson().fromJson(message, NotificationMessage.class);
                            System.out.println(notification.getMessage());
                        }
                        case ERROR -> {
                            ErrorMessage errorMessage = new Gson().fromJson(message, ErrorMessage.class);
                            errorFormat();
                            System.out.println(errorMessage.getErrorMessage());
                            normalFormat();
                        }
                        case LOAD_GAME -> {
                            LoadGameMessage loadMessage = new Gson().fromJson(message, LoadGameMessage.class);
                            gameData = loadMessage.getGame();
                            if(!loadedGame) {
                                printBoards();
                                listGameOptions();
                                loadedGame = true;
                            } else {
                                System.out.println("An update has been made, please refresh the board");
                            }
                        }
                    }
                }
            });
            send(json);
        } catch(Exception ex) {
            return false;
        }
        return true;
    }

    public void  inGame(boolean isObserver) {
        System.out.println("\nSuccessfully joined game.");
        Scanner scan = new Scanner(System.in);
        String input = "start";
        boolean stop = false;

        //printBoards();

        if(!isObserver) {
            while (!stop) {
                input = scan.nextLine();

                if (input.equals("1") || input.equalsIgnoreCase("help")) {
                    listGameExplanations();
                } else if (input.equals("2") || input.equalsIgnoreCase("redraw chess board") || input.equals("redraw")) {
                    printBoards();
                } else if (input.equals("3") || input.equalsIgnoreCase("leave game") || input.equals("leave")) {
                    try {
                        String json = new Gson().toJson(new LeaveGameCommand(authToken, gameID));
                        send(json);
                    } catch(Exception ex) {
                        System.out.println("Leave Game Message Sending Error");
                    }
                    stop = true;
                } else if (input.equals("4") || input.equalsIgnoreCase("make move")) {
                    System.out.println("Need to implement make move");
                    try {
                        send("Make Move Message");
                    } catch(Exception ex) {
                        System.out.println("Make Move Message Sending Error");
                    }
                } else if (input.equals("5") || input.equalsIgnoreCase("resign")) {
                    System.out.println("Need to inplement resign");
                    try {
                        send("Resign Message");
                    } catch(Exception ex) {
                        System.out.println("Resign Message Sending Error");
                    }
                } else if (input.equals("6") || input.equalsIgnoreCase("highlight legal moves") || input.equalsIgnoreCase("highlight")) {
                    System.out.println("Need to implement highlight legal moves");
                    try {
                        send("Highlight Legal Moves Message");
                    } catch(Exception ex) {
                        System.out.println("Highlight Legal Moves Message Sending Error");
                    }
                }
                listGameOptions();
                System.out.println("\nPlease input your selection: ");
            }
        } else {
            while(!stop) {
                listObserveOptions();
                System.out.println("\nPlease input your selection: ");
                input = scan.nextLine();

                if(input.equals("1") || input.equalsIgnoreCase("help")) {
                    listObserveExplanations();
                } else if(input.equals("2") || input.equalsIgnoreCase("quit")) {
                    try {
                        String json = new Gson().toJson(new LeaveGameCommand(authToken, gameID));
                        send(json);
                    } catch(Exception ex) {
                        System.out.println("Leave Game Message Sending Error");
                    }
                    stop = true;
                } else if(input.equals("3") || input.equalsIgnoreCase("redraw chess board") || input.equalsIgnoreCase("redraw")) {
                    printBoards();
                }
            }
        }
    }

    static void listObserveOptions() {
        System.out.println("\n 1. Help");
        System.out.println(" 2. Quit");
        System.out.println(" 3. Redraw Chess Board");
    }

    static void listObserveExplanations() {
        System.out.println("\nInput 1 or help to show this list again");
        System.out.println("Input 2 or quit to leave the game");
        System.out.println("Input 3 or redraw chess board to redraw the chess board.\n" +
                "  (Best after being notified that a move has been made.)");
    }

    static void listGameOptions() {
        System.out.println("\n 1. Help");
        System.out.println(" 2. Redraw Chess Board");
        System.out.println(" 3. Leave Game");
        System.out.println(" 4. Make Move");
        System.out.println(" 5. Resign");
        System.out.println(" 6. Highlight Legal Moves");
    }

    static void listGameExplanations() {
        System.out.println("\nInput 1 or help to show this list again");
        System.out.println("Input 2 or redraw chess board to redraw the chess board.\n" +
                "  (Best after being notified that a move has been made.)");
        System.out.println("Input 3 or leave game to leave the game and let others take your place in the game");
        System.out.println("Input 4 or make move to make a move when it is your turn. Example move: c7c8->Queen\n" +
                "  (c7 is the starting position, c8 is the end position, and ->Queen is the promotion)");
        System.out.println("Input 5 or resign to resign the game and have your opponent win.");
        System.out.println("Input 6 or highlight legal moves. You can input a position where a piece is, like d6\n" +
                "  (This will highlight all possible moves from the starting position.");
    }

    public void printBoards() {
        GameBoardUI ui = new GameBoardUI(gameData.game().getBoard().getBoard());
        printInfo();
        if(teamColor.equalsIgnoreCase("BLACK")) {
            ui.printBlackSideBoard();
        }
        else {
            ui.printWhiteSideBoard();
        }
    }

    public void printInfo() {
        System.out.println("\nGame number: " + gameData.gameID() + ", Game name: " + gameData.gameName());
        System.out.println("White username: " + ((gameData.whiteUsername() == null) ? "<Empty>" : gameData.whiteUsername()) + ", Black username: " + ((gameData.blackUsername() == null) ? "<Empty>" : gameData.blackUsername()));
        System.out.println("Current game condition: " + ((gameData.game().isGameOver()) ? "Finished" : "Ongoing"));
    }

    public void errorFormat() {

    }

    public void normalFormat() {

    }
}
